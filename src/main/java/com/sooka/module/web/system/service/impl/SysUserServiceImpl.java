package com.sooka.module.web.system.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sooka.common.annotation.SysLog;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.exception.SystemException;
import com.sooka.common.utils.CheckSumUtil;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.Const;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.EmailUtil;
import com.sooka.common.utils.HtmlKit;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.PinyinUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.common.utils.encrypt.Encrypt;
import com.sooka.component.shiro.PasswordKit;
import com.sooka.module.web.cms.service.JlinfoService;
import com.sooka.module.web.system.service.SysUserService;
import com.sooka.module.web.system.vo.UserVo;
import com.sooka.module.web.system.vo.tb_yxyz;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.module.web.system.vo.tb_zhjhjl;
import com.sooka.mybatis.mapper.TBZHJHJLMapper;
import com.sooka.mybatis.mapper.TCmsSiteMapper;
import com.sooka.mybatis.mapper.TSysOrgUserMapper;
import com.sooka.mybatis.mapper.TSysPermissionMapper;
import com.sooka.mybatis.mapper.TSysRoleMapper;
import com.sooka.mybatis.mapper.TSysUserMapper;
import com.sooka.mybatis.mapper.TSysUserRoleMapper;
import com.sooka.mybatis.mapper.TbYxyzMapper;
import com.sooka.mybatis.mapper.TbzhInfoMapper;
import com.sooka.mybatis.model.Jlinfo;
import com.sooka.mybatis.model.TCmsSite;
import com.sooka.mybatis.model.TSysOrgUser;
import com.sooka.mybatis.model.TSysPermission;
import com.sooka.mybatis.model.TSysRole;
import com.sooka.mybatis.model.TSysUser;
import com.sooka.mybatis.model.TSysUserRole;

/**
 * Description:后台用户控制器
 *
 *
 * @create 2017-04-13
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

	@Value("${system.site.name}")
	private String siteName;
	@Value("${system.http.host}")
	private String httpIp;
	@Value("${com.sooka.module.web.system.vo.EmailVo}")
	private String MethodName;
	@Autowired
	private TSysUserMapper sysUserMapper;
	@Autowired
	private JlinfoService jlService;
	@Autowired
	private TSysRoleMapper sysRoleMapper;

	@Autowired
	private TSysPermissionMapper sysPermissionMapper;

	@Autowired
	private TSysUserRoleMapper userRoleMapper;

	@Autowired
	private TSysOrgUserMapper userOrgMapper;

	@Autowired
	private TCmsSiteMapper siteMapper;

	@Autowired
	private TBZHJHJLMapper tlMapper;
	
	@Autowired
	private TbzhInfoMapper infoMapper;
	@Autowired
	private EmailUtil em;
	@Autowired
	private TbYxyzMapper yxyzMapper;
	

	@Override
	@SysLog("后台用户登陆")
	public Map<String, Object> login(HttpServletRequest request,
			String username, String password, String remberMe) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("success", false);
		HttpSession session = request.getSession();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
				username, password);
		/* 是否需要记住我 */
		if ("true".equals(remberMe)) {
			usernamePasswordToken.setRememberMe(true);
		}
		try {
			currentUser.login(usernamePasswordToken);
			TSysUser user = findSysUserByUsername(username);
			user.setLoginTime(new Date());
			user.setLastIp(ControllerUtil.getRemoteAddress(request));
			/* 更新用户的登陆信息 */
			sysUserMapper.updateByPrimaryKey(user);
			/* userVo和TSysUser没什么区别，只是增加了siteId */
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(userVo, user);
			if (userVo.getUserId() == 1) {
				userVo.setSiteId(4);
				userVo.setSiteName(siteName);
				/* 设置session */
				session.setAttribute(CmsConst.SITE_USER_SESSION_KEY, userVo);
				result.put("success", true);
				result.put("message", "登录成功！");
			} else {
				List<TCmsSite> sites = siteMapper.selectSitesByUserId(userVo
						.getUserId());
				if (!CmsUtil.isNullOrEmpty(sites)) {
					/* 取出列表中第一个站点的Id,当作此登陆站点的标识 */
					for (TCmsSite site : sites) {
						userVo.setSiteId(site.getSiteId());
						userVo.setSiteName(site.getSiteName());
						break;
					}
					/* 设置session */
					session.setAttribute(CmsConst.SITE_USER_SESSION_KEY, userVo);
					result.put("success", true);
					result.put("message", "登录成功！");
				} else {
					/* 当前用户没有可以管理的站点 */
					result.put("message", "当前用户没有可以管理的站点！");
				}
			}
		} catch (UnknownAccountException e) {
			result.put("message", "账号输入错误！");
		} catch (IncorrectCredentialsException e) {
			result.put("message", "密码输入错误！");
		} catch (LockedAccountException e) {
			result.put("message", "当前账号已被停用！");
		} catch (AuthenticationException ae) {
			result.put("message", "账号或者密码输入错误！");
		} catch (Exception e) {
			result.put("message", "发生了一个错误！");
		}
		return result;
	}

	@Transactional(transactionManager = "masterTransactionManager", rollbackFor = Exception.class)
	@Override
	public String save(TSysUser user, Integer[] roleIds, String orgIds) {
		if (CmsUtil.isNullOrEmpty(roleIds) || CmsUtil.isNullOrEmpty(orgIds)) {
			throw new SystemException("用户角色和部门不能为空！");
		}
		user.setUsername(PinyinUtil.convertLower(HtmlKit.getText(user
				.getUsername())));
		user.setCreateTime(new Date());
		/* 加工password */
		if (!StrUtil.isBlank(user.getPassword().trim())) {
			String salt = CheckSumUtil.getMD5(user.getUsername().trim());
			user.setPassword(PasswordKit.encodePassword(user.getPassword()
					.trim(), salt));
			user.setSalt(salt);
		}
		if (sysUserMapper.insert(user) > 0) {
			if (CmsUtil.isNullOrEmpty(roleIds)) {
				throw new SystemException("请选择用户角色！");
			}
			for (int roleId : roleIds) {
				this.saveUserRole(user.getUserId(), roleId);
			}
			for (String orgId : orgIds.split(",")) {
				this.saveUserOrg(user.getUserId(), Integer.parseInt(orgId));
			}
			return JsonUtil.toSUCCESS("保存成功", "sysUser", true);
		}
		return JsonUtil.toERROR("更新失败！");
	}

	@Transactional(transactionManager = "masterTransactionManager", rollbackFor = Exception.class)
	@Override
	public String saveAccount(tb_zh_info info) throws Exception{
		info.setZh_id(StrUtil.getUUID());
		// 个人注册
		if (info.getZh_sf() == 0) {
			info.setZh_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
			if (infoMapper.insertSelective(info) > 0) {
				Jlinfo jl = new Jlinfo();
				jl.setJianliId(UuidUtil.get32UUID());
				jl.setZhId(info.getZh_id());
				jl.setJianliYx(info.getZh_user());
				jlService.save(jl);
				return JsonUtil.toSUCCESS("保存成功!", "accountNumber", true);
			}
			return JsonUtil.toERROR("保存失败!");
			// 企业注册
		} else if (info.getZh_sf() == 1) {
			info.setZh_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
			infoMapper.insertSelective(info);
			InsertAndnotSendMail(info);
			return JsonUtil.toSUCCESS("保存成功!", "accountNumber", true);
		}
			return JsonUtil.toERROR("保存失败!");

	}
	public void InsertAndnotSendMail(tb_zh_info info)  throws Exception{
		// TODO Auto-generated method stub
		tb_zhjhjl jhjl = new tb_zhjhjl();
		jhjl.setJhjl_id(StrUtil.getUUID());
		jhjl.setJhjl_zh(info.getZh_user());
		jhjl.setJhjl_key(StrUtil.getUUID());
		jhjl.setJhjl_pwd(info.getZh_pwd());
		tlMapper.insert(jhjl);
		String url = "http://"+httpIp + "/" + MethodName + "?name=" + jhjl.getJhjl_zh()
				+ "&key=" + jhjl.getJhjl_key();
		em.sendMail(info.getZh_user(), "您好请点击此链接激活您的账户:<br/>"+url, "白城市公共人才创业就业服务平台");
	}

	@Transactional(transactionManager = "masterTransactionManager", rollbackFor = Exception.class)
	@Override
	public String updateAccount(tb_zh_info info)throws Exception {
		// 个人注册
		if (info.getZh_sf() == 0) {
			info.setZh_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
			if (infoMapper.updateByZhId(info)) {
				Jlinfo jl = new Jlinfo();

				jl.setZhId(info.getZh_id());
				jl.setJianliYx(info.getZh_user());
				Jlinfo jlincz=null;
				 jlincz=jlService.findByUserCodeId(jl.getZhId());
				if(jlincz==null){
					jl.setJianliId(UuidUtil.get32UUID());
					jlService.save(jl);
				}else{
					jlService.update(jl);
				}

				return JsonUtil.toSUCCESS("保存成功!", "accountNumber", true);
			}
			// 企业注册
		}else if (info.getZh_sf() == 1) {
			info.setZh_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
			infoMapper.updateByZhId(info);
		//	updateAndSendMail(info);
			return JsonUtil.toSUCCESS("保存成功!", "accountNumber", true);
		}
			return JsonUtil.toERROR("保存失败!");

	}

	@Transactional(transactionManager = "masterTransactionManager", rollbackFor = Exception.class)
	@Override
	public String update(TSysUser user, Integer[] roleIds, String orgIds) {
		user.setUsername(PinyinUtil.convertLower(HtmlKit.getText(user
				.getUsername())));
		/* 加工password */
		if (!StrUtil.isBlank(user.getPassword().trim())) {
			String salt = CheckSumUtil.getMD5(user.getUsername().trim());
			user.setPassword(PasswordKit.encodePassword(user.getPassword()
					.trim(), salt));
			user.setSalt(salt);
		}
		if (sysUserMapper.updateByPrimaryKey(user) > 0) {
			userRoleMapper.deleteByUserId(user.getUserId());
			if (CmsUtil.isNullOrEmpty(roleIds)) {
				throw new SystemException("请选择用户角色！");
			}
			for (Integer roleId : roleIds) {
				this.saveUserRole(user.getUserId(), roleId);
			}
			if (!StrUtil.isBlank(orgIds)) {
				this.userOrgMapper.deleteByUserId(user.getUserId());
				for (String orgId : orgIds.split(",")) {
					this.saveUserOrg(user.getUserId(), Integer.parseInt(orgId));
				}
			}
			return JsonUtil.toSUCCESS("更新成功", "sysUser", true);
		}
		return JsonUtil.toERROR("更新失败！");
	}

	public void saveUserRole(Integer userId, Integer roleId) {
		TSysUserRole userRole = new TSysUserRole();
		userRole.setRoleId(roleId);
		userRole.setUserId(userId);
		userRoleMapper.insert(userRole);
	}

	public void saveUserOrg(Integer userId, Integer orgId) {
		TSysOrgUser orgUser = new TSysOrgUser();
		orgUser.setUserId(userId);
		orgUser.setOrgId(orgId);
		this.userOrgMapper.insertSelective(orgUser);
	}

	@Override
	public Set<String> findSysUserPermissionsByUsername(String username) {
		/* 根据用户名查询权限 */
		List<TSysPermission> permissions = sysPermissionMapper
				.selectSysUserPermissionsByUsername(username);
		Set<String> set = new HashSet<>();
		for (TSysPermission permission : permissions) {
			set.add(permission.getName());
		}
		return set;

	}

	@Override
	public Set<String> findSysUserRolesPByUserame(String username) {
		List<TSysRole> roles = sysRoleMapper
				.selectSysUserRolesByUsername(username);
		Set<String> set = new HashSet<>();
		for (TSysRole role : roles) {
			set.add(role.getRolename());
		}
		return set;
	}

	@Override
	public TSysUser findSysUserByUsername(String username) {
		return sysUserMapper.selectByUsername(username);
	}

	@Override
	public TSysUser findSysUserByUserId(int userId) {
		return sysUserMapper.selectByPrimaryKey(userId);
	}

	@Override
	public PageInfo<TSysUser> findSysUserPageInfo(Integer pageNumber,
			Integer pageSize, UserVo user) {
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo(sysUserMapper.selectByCondition(user));
	}

	@Override
	@Transactional
	public String Delete(Integer[] ids) {
		boolean flag_ = false;
		UserVo userVo = ((UserVo) ControllerUtil.getHttpSession().getAttribute(
				CmsConst.SITE_USER_SESSION_KEY));
		if (CmsUtil.isNullOrEmpty(userVo)) {
			throw new UnauthenticatedException();
		}
		if (ids.length > 0) {
			for (int id : ids) {
				if (id == 1) {
					throw new SystemException("不能删除超级管理员！");
				}
				if (id == userVo.getUserId()) {
					throw new SystemException("你不能删除自己！");
				}
				if (sysUserMapper.deleteByPrimaryKey(id) > 0) {

					flag_ = userRoleMapper.deleteByUserId(id) > 0;
				}
			}
		}
		if (flag_) {
			return JsonUtil.toSUCCESS("删除用户成功!","sysUser",true);
		}
		return JsonUtil.toERROR("删除用户失败!");
	}

	@Override
	public String deleteAccount(String[] ids) {
		if(ids!=null&&ids.length>0){
			for(String zh_id:ids){
				sysUserMapper.deleteAccount(zh_id);
			}
			return JsonUtil.toSUCCESS("删除成功!","accountNumber",true);
		}
		return JsonUtil.toERROR("删除失败!");
	}
	@Override
	public String deleteAccount(String id) {
		if(StringUtils.isNotBlank(id)){
				sysUserMapper.deleteAccount(id);
			return JsonUtil.toSUCCESS("删除成功!","accountNumber",true);
		}
		return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public String changePassword(Integer userId, String oldPassword,
			String newPassword) {
		TSysUser user = findSysUserByUserId(userId);
		if (PasswordKit.isPasswordValid(user.getPassword(), oldPassword,
				user.getSalt())) {
			user.setPassword(PasswordKit.encodePassword(newPassword,
					user.getSalt()));
			sysUserMapper.updateByPrimaryKey(user);
			return JsonUtil.toSUCCESS("密码修改成功！", "changepwd_page", true);
		}
		return JsonUtil.toERROR("原密码输入错误！");
	}

	@Override
	public Integer countAll() {
		return sysUserMapper.countUser();
	}

	@Override
	public void InsertAndSendMail(tb_zh_info info)  throws Exception{
		// TODO Auto-generated method stub
		tb_zhjhjl jhjl = new tb_zhjhjl();
		jhjl.setJhjl_id(StrUtil.getUUID());
		jhjl.setJhjl_zh(info.getZh_user());
		jhjl.setJhjl_key(StrUtil.getUUID());
		jhjl.setJhjl_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
		tlMapper.insert(jhjl);
		String url = "http://"+httpIp + "/" + MethodName + "?name=" + jhjl.getJhjl_zh()
				+ "&key=" + jhjl.getJhjl_key();
		em.sendMail(info.getZh_user(), "您好请点击此链接激活您的账户:<br/>"+url, "白城市公共人才创业就业服务平台");
	}

	@Override
	public void updateAndSendMail(tb_zh_info info) throws Exception{
		// TODO Auto-generated method stub
		tb_zhjhjl jhjl = tlMapper.selectByZhId(info.getZh_id());
		jhjl.setJhjl_id(jhjl.getJhjl_id());
		jhjl.setJhjl_zh(info.getZh_user());
		jhjl.setJhjl_key(StrUtil.getUUID());
		jhjl.setJhjl_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
		tlMapper.updateByPrimaryKey(jhjl);
		String url = "http://"+httpIp + "/" + MethodName + "?name=" + jhjl.getJhjl_zh()
				+ "&key=" + jhjl.getJhjl_key();
		em.sendMail(info.getZh_user(), "您好请点击此链接激活您的账户:<br/>"+url, "白城市公共人才创业就业服务平台");
	}


	@Override
	public String selectTBZHJHJLByNameAndKey(String name, String key) throws Exception {
		 Example example = new Example(tb_zhjhjl.class);
		  example.createCriteria().andCondition("JHJL_ZH='"+name+"' and JHJL_KEY='"+key+"'");
		tb_zhjhjl tb_zhjhjl = tlMapper.selectOneByExample(example);
		if (StrUtil.checkObjAllFieldsIsNull(tb_zhjhjl)) {
			return "验证码已过期";
		}
		else {
			tb_zh_info info=new tb_zh_info();
				info.setZh_id(StrUtil.getUUID());
				info.setZh_user(tb_zhjhjl.getJhjl_zh());
				info.setZh_pwd(tb_zhjhjl.getJhjl_pwd());
				info.setZh_sf(1);
			if (this.QueryWhetherTheMailBoxIsUnique(info.getZh_user()) <= 0) {
				infoMapper.insert(info);
				return "激活成功 请等待管理员审批";
			}
 else {
				return "您已经激活过当前的邮箱了";
			}
		}
	
	}

	
	public void Register (tb_zh_info info) throws Exception{
		info.setZh_pwd(Encrypt.encode(info.getZh_pwd(), "privateKey"));
		infoMapper.insertSelective(info);
	}

	@Override
	public int QueryWhetherTheMailBoxIsUnique(String  zh_user) {
		// TODO Auto-generated method stub
		return infoMapper.QueryWhetherTheMailBoxIsUnique(zh_user);
	}

	@Override
	public tb_zh_info SignInForIndividualsAndBusinesses(tb_zh_info info) throws Exception{
		// TODO Auto-generated method stub

		String password = Encrypt.encode(info.getZh_pwd(), "privateKey");

		info.setZh_pwd(password);
		return  infoMapper.SignInForIndividualsAndBusinesses(info.getZh_user(),info.getZh_pwd());
	}

	@Override
	public void yjyzm(String zh_user, HttpServletRequest request) {
		// TODO Auto-generated method stub
		tb_yxyz yxyz=new tb_yxyz();
		yxyz.setYz_id(StrUtil.getUUID());
		yxyz.setYz_yzm(StrUtil.SixBitRandomNumber());
		yxyz.setYz_yx(zh_user);
		yxyzMapper.insertSelective(yxyz);
		request.getSession().setAttribute("zhmmYzm", yxyz);
		em.sendMail(zh_user,
				"我们已经收到您找回密码的请求，请输入验证码完成密码找回：</br>您的验证码：<a href='javascript:void(0)' style='color:#e77611;text-decoration:none;'>"
						+ yxyz.getYz_yzm() + "（五分钟内有效）</a>" + "", "白城市公共人才创业就业服务平台");
		int time = 60 * 5;
		while (time > 0) {
			time--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		request.getSession().removeAttribute("zhmmYzm");
	 if (request.getSession().getAttribute("zhmmYzm") == null) {
	 yxyzMapper.delete(yxyz);
	 }
	 }


	@Override
	public tb_yxyz selectByYHM(String zh_user) {
		return yxyzMapper.selectByYHM(zh_user);
	}

	@Override
	public boolean emailVerificationCode(String zh_user, String yzm) {
		Example example=new Example(tb_yxyz.class);
		example.createCriteria().andCondition("yz_yx='"+zh_user+"' and yz_yzm='"+yzm+"'");
		List<tb_yxyz> selectByExample = yxyzMapper.selectByExample(example);
		if(selectByExample.isEmpty()){
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void ChangePassword(String zh_user, String zh_pwd) throws Exception{
		// TODO Auto-generated method stub
		tb_zh_info info=new tb_zh_info();
		info.setZh_pwd(Encrypt.encode(zh_pwd, "privateKey"));
		Example example=new Example(tb_zh_info.class);
		example.createCriteria().andCondition("zh_user='"+zh_user+"'");
		infoMapper.updateByExampleSelective(info, example);
		
	}
	@Override
	public tb_zh_info selectJmm(String jmm,HttpServletRequest request) throws Exception{
		String mm = Encrypt.encode(jmm, "privateKey");
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info) session
		      .getAttribute(Const.ZhInfoLoginSession);
		String zh_user = user.getZh_user();
		return infoMapper.selectJmm(mm,zh_user);
	}

	@Override
	public tb_zh_info selectByZhId(String zh_id) {
		return infoMapper.selectByZhId(zh_id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<tb_zh_info> selectAll(Integer pageNumber,Integer pageSize, tb_zh_info info) {
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo(infoMapper.selectZhAll(info));
	}

	@Override
	public List<tb_zh_info> selectAll() {
		return infoMapper.selectZh();
	}

}

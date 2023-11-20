package com.sooka.module.web.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.sooka.module.web.system.vo.UserVo;
import com.sooka.module.web.system.vo.tb_yxyz;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.mybatis.model.TSysUser;

/**
 * Created by binary on 2017/4/13.
 */
public interface SysUserService{

    /**
     * 用户登陆
     * @param username
     * @param password
     * @param remberMe
     * @return
     */

    Map<String, Object> login(HttpServletRequest request, String username, String password, String remberMe);


    String save(TSysUser user,Integer[] roleIds,String orgIds);

    String saveAccount(tb_zh_info info) throws Exception;

    String update(TSysUser user,Integer[] roleIds,String orgIds);

    String updateAccount(tb_zh_info info) throws Exception;


    /**
     * 根据用户名查询用户权限
     * @param username
     * @return
     */
    Set<String> findSysUserPermissionsByUsername(String username);

    /**
     * 根据用户名查询用户角色
     * @param username
     * @return
     */
    Set<String> findSysUserRolesPByUserame(String username);

    /**
     * 根据用户名查询后台用户
     * @param username
     * @return
     */
    TSysUser findSysUserByUsername(String username);


    /**
     * 根据Id查询后台用户
     * @param userId
     * @return
     */
    TSysUser findSysUserByUserId(int userId);



    /**
     * 根据条件查询系统用户分页
     * @return
     */
    PageInfo<TSysUser> findSysUserPageInfo(Integer pageNumber,Integer pageSize, UserVo user);


    /**
     * 批量删除管理员
     * @param ids
     * @return
     */
    String Delete(Integer[] ids);

    /**
     * 批量删除账户
     * @param ids
     * @return
     */
    String deleteAccount(String[] ids);
    String deleteAccount(String id);



    String changePassword(Integer userId,String oldPassword,String newPassword);

    Integer countAll();
    
    /**
     *  插入账号激活记录表数据 并且 发送邮件
     * @param sysuser user表中的name 和 email
     * 
     */
    public void InsertAndSendMail(tb_zh_info info) throws Exception;

    public void updateAndSendMail(tb_zh_info info)throws Exception;

    /**
     * 用户点击链接查询是否有数据            如有 修改审核的状态
     * @param tb TB_ZHJHJL的实体类
     * @return true 有， false 无
     */
	public String selectTBZHJHJLByNameAndKey(String name, String key) throws Exception;

    /**
     * 新增 
     * @param info  实体类  有空值也可添加
     */
    public void Register (tb_zh_info info) throws Exception;
    /**
     * 查询邮箱是否注册过 0没有 >0 有注册过 
     * @param info
     * @return
     */
    public int QueryWhetherTheMailBoxIsUnique(String zh_user);
    /**
     * 登录
     * @param info
     * @return
     */
    public tb_zh_info SignInForIndividualsAndBusinesses(tb_zh_info info) throws Exception;
    /**
     * 
     * 发送验证码
     * @param zh_user
     */
	public void yjyzm(String zh_user, HttpServletRequest request);
    
    /**
     * 根据邮编查询验证码表
     */
    public tb_yxyz selectByYHM(String zh_user);
    
    /**
     * 根据用户名和验证码查找数据 为空 表中无数据
     * @param zh_user
     * @param yzm
     * @return
     */
    public  boolean emailVerificationCode(String zh_user,String yzm);
    
    public void ChangePassword(String zh_user,String zh_pwd) throws Exception;

    public tb_zh_info selectJmm(String jmm,HttpServletRequest request) throws Exception;

    public tb_zh_info selectByZhId(String zh_id);

    /**
     * 查询所有账号
     * @return
     */
    public PageInfo<tb_zh_info> selectAll(Integer pageNumber,Integer pageSize,tb_zh_info info);

    public List<tb_zh_info> selectAll();


}

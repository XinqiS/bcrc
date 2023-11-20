package com.sooka.module.web.system;


import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.*;
import com.sooka.common.utils.encrypt.Encrypt;
import com.sooka.module.web.cms.service.*;
import com.sooka.module.web.system.service.*;
import com.sooka.module.web.system.vo.*;
import com.sooka.mybatis.model.*;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description:用户管理
 *
 *
 * @create 2017-04-06
 **/
@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private JlbanbenService jlbanbenService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrganizationService orgService;


    @Autowired
    private GwInfoService gwinfoservice;

    @Autowired
    private QyInfoService qyInfoService;

    @Autowired
    private JlinfoService jlinfoservice;

    @Autowired
    private JlqzyxService jlyxService;

    @Autowired
    private JlgzjyService  gzjyService;

    @Autowired
    private JljyjlService  jyjlService;

    @Autowired
    private JlxnryService  xnryService;

    @Autowired
    private JlxnzwService  xnzwService;

    @Autowired
    private ZwscjlService zwscjlService;

    @Autowired
    private ZwtdService  zwtdjlService;

    @RequestMapping
    public ModelAndView index(){
        return new ModelAndView("system/admin");
    }

    /* 后台用户列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50") Integer pageSize, UserVo user){
        ModelAndView view = new ModelAndView("system/admin_list");
        view.addObject("model",sysUserService.findSysUserPageInfo(pageNumber,pageSize,user));
        view.addObject("user", user);
        return view;

    }

    /* 后台用户修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "userId",required = false) Integer userId, Model model){
        if(userId!=null) {
            model.addAttribute("user", sysUserService.findSysUserByUserId(userId));
            model.addAttribute("userRole", roleService.findByUserId(userId));
        }
        model.addAttribute("orgList",orgService.findByPid(0));
        model.addAttribute("roleList",roleService.findAll());
        return "system/admin_input";
    }

    /* 后台用户更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(TSysUser user,@RequestParam(value = "roleId",required = false) Integer[] roleIds,@RequestParam(value = "orgId",required = false) String orgIds ){
        if(user.getUserId()!=null) {
            return sysUserService.update(user,roleIds,orgIds);
        }
        return sysUserService.save(user,roleIds,orgIds);
    }


    /* 后台用户删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("userId") Integer[] id){
        return sysUserService.Delete(id);
    }


    @RequestMapping("/valid/username")
    @ResponseBody
    public String validSysUserName(@RequestParam("username") String name){
          if(sysUserService.findSysUserByUsername(name)!=null) {
              return "{\"error\": \"名字已经被占用啦\"}";
          }
              return "{\"ok\": \"名字很棒\"}";
    }


    /* 账号列表 */
    @RequestMapping("/accountNumber")
    public ModelAndView accountNumber(
            @RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="30") Integer pageSize, tb_zh_info info)throws Exception{
        ModelAndView view = new ModelAndView("system/account_number");
        PageInfo<tb_zh_info> list = sysUserService.selectAll(pageNumber,pageSize,info);
        for(int i=0;i<list.getList().size();i++){
            String mm = list.getList().get(i).getZh_pwd();
            list.getList().get(i).setZh_pwd(Encrypt.decode(mm, "privateKey"));
        }
        view.addObject("model",list);
        view.addObject("info", info);
        return view;
    }

    /* 后台用户修改 */
    @RequestMapping("/editAccount")
    public String editAccount(@RequestParam(value = "zh_id",required = false) String zh_id, Model model)throws Exception{
        if(zh_id != null) {
            tb_zh_info info = sysUserService.selectByZhId(zh_id);
            info.setZh_pwd(Encrypt.decode(info.getZh_pwd(), "privateKey"));
            model.addAttribute("zh",info);
        }
        return "system/edit_account";
    }
    /* 后台用户删除 */
    @RequestMapping("/deleteAccount")
    @ResponseBody
    public String deleteAccount(
            @RequestParam(value = "zh_id",required = false) String zh_id)throws Exception{
        tb_zh_info info = sysUserService.selectByZhId(zh_id);
        if(info.getZh_sf()==1){
            //删除企业信息
            tb_qy_info qyInfo=null;
            qyInfo= qyInfoService.selectAll(info.getZh_id());
            if(qyInfo!=null){
                return JsonUtil.toSUCCESS("存在企业信息，删除失败!!", "accountNumber", true);
            }
            tb_gw_info gwinfo=null;
            gwinfo=gwinfoservice.selectOne(info.getZh_id());
            if(gwinfo!=null){
                return JsonUtil.toSUCCESS("存在企业信息，删除失败!!", "accountNumber", true);
            }
            if(qyInfo==null && gwinfo==null){
                return sysUserService.deleteAccount(zh_id);
            }

        }else{
            //删除个人信息
            Jlbanben jlbanben=new Jlbanben();
            Jlinfo jlinfo=null;
            jlinfo=jlinfoservice.findByUserCodeId(info.getZh_id());
            if(jlinfo!=null){
                jlbanben.setJianliId(jlinfo.getJianliId());
                //删除基本信息
                jlinfoservice.deleteByUserCodeId(jlinfo.getZhId());
                List<Jlbanben> jlbanbenlist=jlbanbenService.findList(jlbanben);
                if(jlbanbenlist.size()>0){
                    for(int i=0;i<jlbanbenlist.size();i++){
                        Jlbanben jlba=jlbanbenlist.get(i);
                        //删除简历版本信息
                        jlbanbenService.deletejlBBid(jlba.getJlBbId());
                        Jlqzyx qzyx=null;
                        qzyx = jlyxService.findJlqzyxByJlBanbenId(jlba.getJlBbId());
                        if(qzyx!=null){
                            //删除求职意向、年收入、技能特长
                            jlyxService.deleteJlqzyxByJlBanbenId(qzyx.getJlBbId());
                        }
                        //删除工作经验
                       List<Jlgzjy>  gzjyList=gzjyService.findGzjyByJibanbenId(jlba.getJlBbId());
                       if(gzjyList.size()>0){
                           gzjyService.deleteGzjyByJibanbenId(jlba.getJlBbId());
                       }
                       //删除教育经历
                       List<Jljyjl>  jljyjlList=jyjlService.findJyjlByJibanbenId(jlba.getJlBbId());
                       if(jljyjlList.size()>0){
                           jyjlService.deleteJyjlByJibanbenId(jlba.getJlBbId());
                       }
                       //删除校内荣誉
                       List<Jlxnry> xnryList=xnryService.findXnryByJibanbenId(jlba.getJlBbId());
                       if(xnryList.size()>0){
                           xnryService.deleteXnryByJibanbenId(jlba.getJlBbId());
                       }
                       //删除校内职务
                       List<Jlxnzw> xnzwList=xnzwService.findXnzwByJibanbenId(jlba.getJlBbId());
                       if(xnzwList.size()>0){
                           xnzwService.deleteXnzwByJibanbenId(jlba.getJlBbId());
                       }

                    }

                }
                //删除职位收藏
                Map<String, Object> map=new HashMap<String, Object>();
                map.put("ZH_ID", info.getZh_id());
                List<Map<String,Object>> zwscList= zwscjlService.findZwscjlByZhIdquery(map);
                if(zwscList.size()>0){
                    zwscjlService.deleteZhiId(info.getZh_id());
                }
                //删除职位申请
                List<Map<String,Object>> zwtcList=zwtdjlService.findZwtdjlByZhIdquery(map);
                if(zwtcList.size()>0){
                    zwtdjlService.deleteZhiId(info.getZh_id());
                }

            }
            return sysUserService.deleteAccount(zh_id);



        }
        return JsonUtil.toERROR("删除失败!");
    }
    /**
     * 新增用户前确认邮箱是否存在
     *
     * @param info
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(tb_zh_info info) throws Exception{
        if("".equals(info.getZh_id())|| info.getZh_id()==null) {
            return  sysUserService.saveAccount(info);
        }
        tb_zh_info infocle = sysUserService.selectByZhId(info.getZh_id());
        if(infocle.getZh_sf()!=info.getZh_sf()){
            if(infocle.getZh_sf()==1){
                //修改企业信息
                tb_qy_info qyInfo=null;
                qyInfo= qyInfoService.selectAll(infocle.getZh_id());
                if(qyInfo!=null){
                    return JsonUtil.toSUCCESS("存在企业信息，修改失败!", "accountNumber", true);
                }
                tb_gw_info gwinfo=null;
                gwinfo=gwinfoservice.selectOne(infocle.getZh_id());
                if(gwinfo!=null){
                    return JsonUtil.toSUCCESS("存在企业信息，修改失败!", "accountNumber", true);
                }
                if(qyInfo==null && gwinfo==null){
                    return sysUserService.updateAccount(info);
                }

            }else{
                //修改个人信息
                Jlinfo jlinfo=null;
                jlinfo=jlinfoservice.findByUserCodeId(infocle.getZh_id());
                if(jlinfo!=null){
                    return JsonUtil.toSUCCESS("存在简历信息，修改失败!", "accountNumber", true);
                }
                if(jlinfo==null){
                    return sysUserService.updateAccount(info);
                }

            }
        }else{
            return sysUserService.updateAccount(info);
        }
        return JsonUtil.toERROR("修改失败!");
    }
    //职位收藏只检查不保存
    @RequestMapping("zwscor")
    @ResponseBody
    public Map<String, Object> zwscor(tb_zh_info info, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int infocla = sysUserService.QueryWhetherTheMailBoxIsUnique(info.getZh_user());
            if(infocla==0){
                map.put("type", "1");
                map.put("message", "用户可用");
            }else{
                map.put("type", "2");
                map.put("message", "用户已存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
        }
        return map;
    }
   /*  后台账户删除 
    @RequestMapping("/deleteAccount")
    @ResponseBody
    public String deleteAccount(@RequestParam("zh_id") String[] zh_id){
        return sysUserService.deleteAccount(zh_id);
    }
*/
}

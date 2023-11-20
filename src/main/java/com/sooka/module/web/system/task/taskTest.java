package com.sooka.module.web.system.task;

import com.sooka.component.spring.SpringContextHolder;
import com.sooka.module.web.system.service.SysUserService;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 *
 * @create 2017-07-08
 **/
@Component
public class taskTest {

    SysUserService service = SpringContextHolder.getBean(SysUserService.class);

    public void job1(){
        System.out.println("------------------------------------------job1");
    }

    public void job2(){
        System.out.println("------------------------------------------job2");
    }

    public void job3(){
        System.out.println("------------------------------------------job3["+service.findSysUserByUsername("admin").getPassword()+"]");
    }

}

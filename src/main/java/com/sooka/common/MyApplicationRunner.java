package com.sooka.common;

import com.sooka.common.aop.FormTokenAspect;
import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.component.lucene.LuceneManager;
import com.sooka.component.lucene.util.IndexObject;
import com.sooka.module.web.cms.service.ContentService;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TCmsSite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;

@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
@Scope("prototype")
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${system.http.protocol}")
    private String httpProtocol;

    @Value("${system.site.subfix}")
    private String siteSubfix;

    @Value("${system.http.host}")
    private String httpHost;

    @Value("${system.site.prefix}")
    private String sitePrefix;

    @Autowired
    private SiteService siteService;
    @Autowired
    LuceneManager luceneManager;
    @Autowired
    private ContentService contentService;

    private static final Logger log = LoggerFactory.getLogger(FormTokenAspect.class);

    @Override
    public void run(ApplicationArguments applicationArguments)  {
        System.out.println("-------------->" + "项目启动，now=" + new Date());
        //清空防止重复
        System.out.println("-------------->" + "清空lucene索引，now=" + new Date());
        luceneManager.deleteAll();
        System.out.println("-------------->" + "查询所有content数据，now=" + new Date());
        List<TCmsContent> list=contentService.findAll();
        System.out.println("-------------->" + "创建lucene索引，now=" + new Date());



        for (TCmsContent content:list) {
            IndexObject object2 = new IndexObject();
            TCmsSite site = siteService.findById(content.getSiteId());
            if(site!=null){
                String url = httpProtocol + "://" + (StrUtil.isBlank(site.getDomain())?httpHost:site.getDomain()) + "/"+sitePrefix+"/"+site.getSiteId()+"/";
                url+=content.getCategoryId()+"/"+content.getContentId();
                object2.setUrl(url+siteSubfix);
            }else {
                object2.setUrl("");
            }
            try {
                object2.setTitle(content.getTitle());
                object2.setId(content.getContentId().toString());
                object2.setKeywords(content.getKeywords());
                object2.setPostDate(DateUtil.formatDate(content.getInputdate()));
                object2.setDescription(content.getDescription());
                luceneManager.create(object2);
            }catch (Exception e ){
                System.out.println("-------------->" + "创建lucene报错，now=" + e.getMessage());
            }

        }





//        myTimer();
    }

//    public static void myTimer(){
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("------定时任务--------");
//            }
//        }, 0, 1000);
//    }
}
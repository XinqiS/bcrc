package com.sooka.component.beetl.tag;

import com.google.common.collect.Maps;
import org.beetl.core.TagFactory;
import org.beetl.ext.spring.SpringBeanTagFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:aa
 *
 *
 * @create 2017-06-03
 **/
@Component
public class TagRegisterFactory {


    /* 内容列表标签 */
    @Bean(name = "contentListTagFactory")
    public SpringBeanTagFactory ContentListTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("contentListTag");
        return  springBeanTagFactory;
    }


    /* 内容分页标签 */
    @Bean(name = "contentPageTagFactory")
    public SpringBeanTagFactory ContentPageTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("contentPageTag");
        return  springBeanTagFactory;
    }

    /* 分页标签 */
    @Bean(name = "paginationTagFactory")
    public SpringBeanTagFactory PaginationTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("paginationTag");
        return  springBeanTagFactory;
    }
    /* dbt成员分页标签 */
    @Bean(name = "dbtuserPageTagFactory")
    public SpringBeanTagFactory dbtuserPageTagFactory(){
    	SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
    	springBeanTagFactory.setName("dbtUserListPageTag");
    	return  springBeanTagFactory;
    }

    @Bean(name = "contentTagFactory")
    public SpringBeanTagFactory contentTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("contentTag");
        return  springBeanTagFactory;
    }

    @Bean(name = "categoryListTagFactory")
    public SpringBeanTagFactory categoryListTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("categoryListTag");
        return  springBeanTagFactory;
    }

    @Bean(name = "categoryTagFactory")
    public SpringBeanTagFactory categoryTagFactory() {
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("categoryTag");
        return springBeanTagFactory;
    }



    @Bean(name = "printSitePositionTagFactory")
    public SpringBeanTagFactory  printSitePositionTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("printSitePositionTag");
        return  springBeanTagFactory;
    }

    @Bean(name = "searchModelFiledValueTagFactory")
    public SpringBeanTagFactory searchModelFiledValueTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("searchModelFiledValueTag");
        return  springBeanTagFactory;
    }

    @Bean(name = "lucenePageTagFactory")
    public SpringBeanTagFactory lucenePageTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("lucenePageTag");
        return  springBeanTagFactory;
    }


    /* 分页标签 */
    @Bean(name = "lucenePaginationTagFactory")
    public SpringBeanTagFactory lucenePaginationTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("lucenePaginationTag");
        return  springBeanTagFactory;
    }

    /* 分页标签 */
    @Bean(name = "indexSilderTagFactory")
    public SpringBeanTagFactory indexSilderTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("indexSilderTag");
        return  springBeanTagFactory;
    }


    @Bean(name = "topicListTagFactory")
    public SpringBeanTagFactory topicListTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("topicListTag");
        return  springBeanTagFactory;
    }

    @Bean(name = "topicContentTagFactory")
    public SpringBeanTagFactory topicContentTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("topicContentTag");
        return  springBeanTagFactory;
    }


    /* 友情链接标签 */
    @Bean(name = "friendLinkListTagFactory")
    public SpringBeanTagFactory friendLinkListTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("friendLinkListTag");
        return  springBeanTagFactory;
    }

    /* 视频标签 */
    @Bean(name = "videoTagFactory")
    public SpringBeanTagFactory videoTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("videoTag");
        return  springBeanTagFactory;
    }

    /* 投票标签 */
    @Bean(name = "voteTagFactory")
    public SpringBeanTagFactory voteTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("voteTag");
        return  springBeanTagFactory;
    }
    /* 文字直播标签 */
    @Bean(name = "liveListTagFactory")
    public SpringBeanTagFactory liveTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("liveListTag");
        return  springBeanTagFactory;
    }
    /* 代表团标签 */
    @Bean(name = "dbtListTagFactory")
    public SpringBeanTagFactory dbtTagFactory(){
    	SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
    	springBeanTagFactory.setName("dbtListTag");
    	return  springBeanTagFactory;
    }
    /* 代表团成员标签 */
    @Bean(name = "dbtUserListTagFactory")
    public SpringBeanTagFactory dbtUserTagFactory(){
    	SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
    	springBeanTagFactory.setName("dbtUserListTag");
    	return  springBeanTagFactory;
    }
    /* 企业标签 */
    @Bean(name = "enterpriseListTagFactory")
    public SpringBeanTagFactory enterpriseTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("enterpriseListTag");
        return  springBeanTagFactory;
    }
    /* 招聘信息 */
    @Bean(name = "gwinfoListTagFactory")
    public SpringBeanTagFactory GwinfoListTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("gwinfoListTag");
        return  springBeanTagFactory;
    }
    /* 招聘信息 */
    @Bean(name = "zwtdListTagFactory")
    public SpringBeanTagFactory ZwtdListTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("zwtdListTag");
        return  springBeanTagFactory;
    }
    /*  */
    @Bean(name = "topcTagFactory")
    public SpringBeanTagFactory TopcTagFactory(){
        SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
        springBeanTagFactory.setName("topcTag");
        return  springBeanTagFactory;
    }
    /* TagFactory */
    @Bean(name = "tagFactory")
    public Map<String,TagFactory> Tag(@Qualifier("contentListTagFactory") SpringBeanTagFactory contentListTag,
                                      @Qualifier("contentPageTagFactory") SpringBeanTagFactory contentPageTag,
                                      @Qualifier("paginationTagFactory") SpringBeanTagFactory paginationTag,
                                      @Qualifier("contentTagFactory") SpringBeanTagFactory contentTag,
                                      @Qualifier("categoryListTagFactory") SpringBeanTagFactory categoryListTag,
                                      @Qualifier("categoryTagFactory") SpringBeanTagFactory categoryTag,
                                      @Qualifier("searchModelFiledValueTagFactory") SpringBeanTagFactory SearchModelFiledValueTag,
                                      @Qualifier("printSitePositionTagFactory") SpringBeanTagFactory printSitePositionTagFactory,
                                      @Qualifier("lucenePageTagFactory") SpringBeanTagFactory lucenePageTagFactory,
                                      @Qualifier("lucenePaginationTagFactory") SpringBeanTagFactory lucenePaginationTagFactory,
                                      @Qualifier("indexSilderTagFactory") SpringBeanTagFactory indexSilderTagFactory,
                                      @Qualifier("topicListTagFactory") SpringBeanTagFactory topicListTagFactory,
                                      @Qualifier("topicContentTagFactory") SpringBeanTagFactory topicContentTagFactory,
                                      @Qualifier("friendLinkListTagFactory") SpringBeanTagFactory friendLinkListTagFactory,
                                      @Qualifier("videoTagFactory") SpringBeanTagFactory videoTagFactory,
                                      @Qualifier("voteTagFactory") SpringBeanTagFactory voteTagFactory,
                                      @Qualifier("liveListTagFactory") SpringBeanTagFactory liveListTag,
                                      @Qualifier("dbtListTagFactory") SpringBeanTagFactory dbtListTag,
                                      @Qualifier("dbtUserListTagFactory") SpringBeanTagFactory dbtUserListTag,
                                      @Qualifier("dbtuserPageTagFactory") SpringBeanTagFactory dbtUserListPageTag,
                                      @Qualifier("gwinfoListTagFactory") SpringBeanTagFactory gwinfoListTag,
                                      @Qualifier("zwtdListTagFactory") SpringBeanTagFactory zwtdListTag,
                                      @Qualifier("topcTagFactory") SpringBeanTagFactory topcTag,
                                      @Qualifier("enterpriseListTagFactory") SpringBeanTagFactory enterpriseListTag


    ){
        Map<String,TagFactory> tag = Maps.newHashMap();
        tag.put("cms_content_list",contentListTag);
        tag.put("gw_info_list",gwinfoListTag);
        tag.put("zw_td_list",zwtdListTag);
        tag.put("topc_list",topcTag);

        tag.put("cms_content_page",contentPageTag);
        tag.put("cms_pagination",paginationTag);
        tag.put("cms_content",contentTag);
        tag.put("cms_category_list",categoryListTag);
        tag.put("cms_category",categoryTag);
        tag.put("cms_modelfiled_find",SearchModelFiledValueTag);
        tag.put("cms_site_pos",printSitePositionTagFactory);
        tag.put("cms_lucene_page",lucenePageTagFactory);
        tag.put("cms_lucene_pagination",lucenePaginationTagFactory);
        tag.put("cms_index_silder",indexSilderTagFactory);
        tag.put("cms_topic_list",topicListTagFactory);
        tag.put("cms_topic_content",topicContentTagFactory);
        tag.put("cms_friendlink",friendLinkListTagFactory);
        tag.put("cms_index_video",videoTagFactory);
        tag.put("cms_index_vote",voteTagFactory);
        tag.put("cms_live_list",liveListTag);
        tag.put("cms_dbt_list",dbtListTag);
        tag.put("cms_dbtUser_list",dbtUserListTag);
        tag.put("cms_dbtUserPage_list",dbtUserListPageTag);
        tag.put("cms_enterprise_list",enterpriseListTag);
        return  tag;
    }

}

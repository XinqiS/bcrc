package com.sooka.module.api;

import com.github.pagehelper.PageInfo;
import com.sooka.common.annotation.ParamNotNull;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.exception.ApiException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.ModelService;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.mybatis.model.TCmsCategory;
import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TCmsModel;
import com.sooka.mybatis.model.TCmsSite;
import com.sooka.module.web.cms.service.CategoryService;
import com.sooka.module.web.cms.service.ContentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description:内容api
 *
 *
 * @create 2017-05-31
 **/
@RestController
@RequestMapping("/api/content")
public class ContentApiController {

    @Autowired
    SiteService siteService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ContentService contentService;

    @Autowired
    ModelService modelService;

    @ApiOperation("内容列表接口")
    @GetMapping(value = "/list")
    @ParamNotNull(parameter="siteId,categoryId")
    public String list(@RequestParam("siteId") Integer siteId,
                       @RequestParam("categoryId") Long categoryId,
                       @RequestParam(value = "isRecommend",defaultValue = "0") Integer isRecommend,
                       @RequestParam(value = "isHot",defaultValue = "0") Integer isHot,
                       @RequestParam(value = "hasChild",defaultValue = "0") Integer hasChild,
                       @RequestParam(value = "orderBy",defaultValue = "1") Integer orderBy,
                       @RequestParam(value = "isPic",required = false) Integer isPic,
                       @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
                       ){
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new ApiException("["+siteId+"]"+ CmsConst.SITE_NOT_FOUND);
        }
        TCmsCategory category = categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new ApiException("["+categoryId+"]"+CmsConst.CATEGORY_NOT_FOUND);
        }
        PageInfo page = this.contentService.findContentListBySiteIdAndCategoryId(siteId,categoryId,orderBy,pageNumber,pageSize,hasChild,isHot,isPic,isRecommend);
        return JsonUtil.toSuccessResultJSON("请求成功",page.getList());
    }

    @ApiOperation("内容详情接口")
    @GetMapping(value = "/{contentId}")
    public String content(@PathVariable Long contentId){
        TCmsContent content = contentService.findById(contentId);
        if(CmsUtil.isNullOrEmpty(content)) {
            throw new ApiException("["+contentId+"]"+CmsConst.CONTENT_NOT_FOUND);
        }
        TCmsModel contentModel = modelService.findById(content.getModelId());
        Map result = contentService.findContentByContentIdAndTableName(contentId,contentModel.getTableName());
        contentService.viewUpdate(contentId);
        return JsonUtil.toSuccessResultJSON("请求成功",result);
    }

}

package com.sooka.component.beetl.tag.cms;

import com.google.common.collect.Maps;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.mybatis.model.TCmsCategory;
import com.sooka.mybatis.model.TCmsSite;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.CategoryService;
import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description:内容列表标签
 *
 *
 * @create 2017-05-26
 **/
@Service
@Scope("prototype")
public class CategoryTag extends GeneralVarTagBinding {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SiteService siteService;

	@Value("${system.http.protocol}")
	private String httpProtocol;

	@Value("${system.http.host}")
	private String httpHost;

	@Value("${system.site.prefix}")
	private String sitePrefix;

	@Value("${system.site.subfix}")
	private String siteSubfix;

	@Override
	public void render() {
		Map result = Maps.newHashMap();
		Long categoryId=  (this.getAttributeValue("categoryId") instanceof String)?Long.parseLong((String) this.getAttributeValue("categoryId")):(Long) this.getAttributeValue("categoryId");
		String categoryAlias = (String) this.getAttributeValue("categoryAlias");
		Integer isParent=  (this.getAttributeValue("isParent") instanceof String)?Integer.parseInt((String) this.getAttributeValue("isParent")):(Integer) this.getAttributeValue("categoryId");
		TCmsCategory category;
		if(!(("").equals(categoryAlias)||null==categoryAlias)){
			 category = categoryService.findByAlias(categoryAlias);
		}else {
			 category = categoryService.findById(categoryId);
		}
		TCmsSite site = siteService.findById(category.getSiteId());
		if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException("栏目["+categoryId+"]不存在！");
        }
		if(isParent==1&&category.getParentId()!=0) {
            category = categoryService.findById(category.getParentId());
        }
		result.put("categoryId",category.getCategoryId());
		result.put("categoryName",category.getCategoryName());
		result.put("categoryAlias",category.getAlias());
		result.put("categoryContent",category.getContent());
		result.put("categoryIcon",category.getCategoryIcon());
		result.put("enName",category.getEnName());
		result.put("url", !StrUtil.isBlank(category.getUrl())?category.getUrl():httpProtocol + "://" + (StrUtil.isBlank(site.getDomain())?httpHost:site.getDomain()) + "/"+sitePrefix+"/" + category.getSiteId() + "/" + category.getCategoryId()+siteSubfix);
		result.put("more", !StrUtil.isBlank(category.getUrl())?category.getUrl():httpProtocol + "://" + (StrUtil.isBlank(site.getDomain())?httpHost:site.getDomain()) + "/"+sitePrefix+"/" + category.getSiteId() + "/" + category.getCategoryId()+"/index_1"+siteSubfix);
		this.binds(result);
		this.doBodyRender();
	}

}

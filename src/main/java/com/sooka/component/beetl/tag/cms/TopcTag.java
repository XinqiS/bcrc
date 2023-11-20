package com.sooka.component.beetl.tag.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.Pojo2MapUtil;
import com.sooka.module.web.cms.service.PcService;
import com.sooka.module.web.cms.service.TopicService;
import com.sooka.mybatis.model.JlAll;
import com.sooka.mybatis.model.TCmsPc;
import com.sooka.mybatis.model.TCmsTopic;
import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:专题列表标签
 *
 *
 * @create 2017-05-26
 **/
@Service
@Scope("prototype")
public class TopcTag extends GeneralVarTagBinding {

	@Autowired
	private PcService pcService;


	/**
	 * 职位展示
	 *
	 */
	@Override
	public void render() {
		Integer pageNumber =  Integer.parseInt((CmsUtil.isNullOrEmpty(this.getAttributeValue("pageNumber"))?"1":(String) this.getAttributeValue("pageNumber")));
		Integer pageSize =  Integer.parseInt((String) this.getAttributeValue("size"));
		//PageInfo<Map<String, Object>> pageInfo =null;
		TCmsPc jl=new TCmsPc();
		PageInfo<TCmsPc> pageInfo;
		pageInfo = pcService.pagequery(pageNumber,pageSize,jl);
		// pageInfo = zwtdlService.pageAll(pageNumber,pageSize,jl);
		if(CmsUtil.isNullOrEmpty(pageInfo.getList())) {
			return;
		}
		this.wrapRender(pageInfo.getList());
	}
	private void wrapRender(List<TCmsPc>  contentList) {
		int i = 1;
		for (TCmsPc content : contentList) {
			this.binds(content);
			this.doBodyRender();
			i++;
		}

	}

}

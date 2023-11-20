package com.sooka.component.beetl.tag.cms;

import java.util.List;
import java.util.Map;

import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.Pojo2MapUtil;
import com.sooka.module.web.cms.service.LiveService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.model.TCmsFriendlink;
import com.sooka.mybatis.model.TCmsLive;

/**
 * Description:文字直播列表标签
 *
 **/
@Service
@Scope("prototype")
public class LiveListTag extends GeneralVarTagBinding {

	@Autowired
	private LiveService liveService;

	@Override
	public void render() {

		Long contentId = (Long) this.getAttributeValue("contentId");
        TCmsLive live = new TCmsLive();
		if(!CmsUtil.isNullOrEmpty(contentId)) {
			live.setContent_id(contentId.intValue());
        }
		List<TCmsLive> list = liveService.selectForTag(live);
		try {
			this.wrapRender(list);
		} catch (Exception e) {
			throw new CmsException(e.getMessage());
		}

	}

		private void wrapRender(List<TCmsLive> list) throws Exception {
        int i=1;
        for (TCmsLive live : list){
            Map result=Pojo2MapUtil.toMap(live);
			result.put("index",i);
        	this.binds(result);
			this.doBodyRender();
			i++;
		}
	}

}

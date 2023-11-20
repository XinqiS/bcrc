package com.sooka.component.beetl.tag.cms;

import com.sooka.module.web.cms.service.TopicService;
import com.sooka.mybatis.model.TCmsTopic;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.Pojo2MapUtil;
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
public class TopicListTag extends GeneralVarTagBinding {

	@Autowired
	private TopicService topicService;

	@Value("${system.http.protocol}")
	private String httpProtocol;

	@Value("${system.site.subfix}")
	private String siteSubfix;

	@Value("${system.site.prefix}")
	private String sitePrefix;

	@Override
	public void render() {

		Integer siteId=  (this.getAttributeValue("siteId") instanceof String)?Integer.parseInt((String) this.getAttributeValue("siteId")):(Integer)this.getAttributeValue("siteId");
		Integer isRecommend = Integer.parseInt((String) this.getAttributeValue("isRecommend"));
		Integer size = Integer.parseInt((String) this.getAttributeValue("size"));
		try {
			this.wrapRender(siteId,(isRecommend==1?true:false),size);
		} catch (Exception e) {
			throw new CmsException(e.getMessage());
		}

	}
	private void wrapRender(Integer siteId,boolean isRecommend,Integer size) throws Exception {

		List<TCmsTopic> topicList = topicService.findByRecommendList(siteId,isRecommend,size);
        int i = 1;
		if(CmsUtil.isNullOrEmpty(topicList)) {
			return;
		}
        for (TCmsTopic topic : topicList){
			Map result = Pojo2MapUtil.toMap(topic);
			result.put("url",httpProtocol + "://" + ControllerUtil.getDomain() + "/"+sitePrefix+"/" + topic.getSiteId() + "/topic/" + topic.getTopicId()+siteSubfix);
			result.put("index",i);
        	this.binds(result);
			this.doBodyRender();
			i++;
		}
	}

}

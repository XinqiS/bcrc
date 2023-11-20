package com.sooka.component.beetl.tag.cms;

import java.util.List;
import java.util.Map;

import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sooka.common.exception.CmsException;
import com.sooka.common.exception.SystemException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.Pojo2MapUtil;
import com.sooka.module.web.cms.service.EnterpriseService;
import com.sooka.module.web.system.service.QyInfoService;
import com.sooka.module.web.system.vo.tb_qy_info;

/**
 * Description:企业列表标签
 *
 **/
@Service
@Scope("prototype")
public class EnterpriseListTag extends GeneralVarTagBinding {

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private QyInfoService qyinfoService;

	@Override
	public void render() {
		if (CmsUtil.isNullOrEmpty(this.args[1])) {
	            throw  new SystemException("标签参数不能为空！");
	    }
	    Integer isFamous =  Integer.parseInt((String) this.getAttributeValue("isFamous"));
	    Integer size =  Integer.parseInt((String) this.getAttributeValue("size"));
		List<tb_qy_info> list = qyinfoService.selectQyInfoForMq(isFamous, size);
		try {
			this.wrapRender(list);
		} catch (Exception e) {
			throw new CmsException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	private void wrapRender(List<tb_qy_info> list) throws Exception {
        int i=1;
		for (tb_qy_info enterprise : list) {
			@SuppressWarnings("rawtypes")
			Map result = Pojo2MapUtil.toMap(enterprise);
			result.put("index",i);
        	this.binds(result);
			this.doBodyRender();
			i++;
		}
	}

}

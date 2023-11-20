package com.sooka.module.web.cms.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.module.web.cms.vo.TCmsContentVo;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.TCmsContent;


public interface EnterpriseService extends BaseService<tb_qy_info,Integer> {

	List<tb_qy_info> selectFamousEnterprise(Integer isFamous, Integer size);

	PageInfo<tb_qy_info> page(Integer pageNumber, Integer pageSize, tb_qy_info pojo);

	public String deleteByGwId(String[] ids);

	public String approval(tb_qy_info enterprise);
	public String updateSftj(tb_qy_info enterprise,Integer type);


	public tb_qy_info findByQyId(String qy_id);

}

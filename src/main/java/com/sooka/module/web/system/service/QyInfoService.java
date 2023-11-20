package com.sooka.module.web.system.service;

import java.util.List;

import com.sooka.common.base.BaseService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.JlAll;

public interface QyInfoService extends BaseService<tb_qy_info,String>{
	
	public tb_qy_info selectByzhInfoId(String zh_id);

	public tb_qy_info selectAll(String zh_id);

	public tb_qy_info selectByzhId(String zh_id);
	
	public boolean UpdateQyxx(tb_qy_info qy);

	public List<tb_qy_info> selectQyInfoForMq(Integer qy_sftj, Integer size);

	public tb_qy_info selectByPrimaryKey(String qy_id);

	public void deleteRelationByQyId(String id);




}

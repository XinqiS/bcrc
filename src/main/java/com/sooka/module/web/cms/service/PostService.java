package com.sooka.module.web.cms.service;

import java.util.List;

import com.sooka.common.base.BaseService;
import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.module.web.system.vo.tb_qy_info;


public interface PostService extends BaseService<tb_gw_info,Integer> {

	List<tb_gw_info> selectPostByEnterpriseId(tb_gw_info post);

	public String deleteByZwId(String[] ids);

	public String updateSftj(tb_gw_info post,Integer type);
}

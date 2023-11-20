package com.sooka.module.web.system.service;

import com.github.pagehelper.PageInfo;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_zwtdjl;
import com.sooka.mybatis.model.JlAll;

import java.util.List;
import java.util.Map;

public interface ZwtdjlService {
	public tb_zwtdjl selectJlAndMsBysessionId(String id);

	public void updateMsyqByPrimiryKey(String tdjl_id);
	
	public void deleteByPrimiryKey(String tdjl_id);

	PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, JlAll g);

    PageInfo<Map<String, Object>> pageAll(Integer pageNumber, Integer pageSize, JlAll g);

	/*public List<Map<String,Object>> searchTalents(String cshy, String nl, String nl1, String xb, String gznx, String gznx1, String xl, String xl1);*/

}

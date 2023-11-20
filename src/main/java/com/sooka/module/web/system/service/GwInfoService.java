package com.sooka.module.web.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_gw_info;





public interface GwInfoService  extends BaseService<tb_gw_info, String>{
	public void insertQyzxZjgw(tb_gw_info gw,HttpServletRequest request);
	
	public PageInfo<tb_gw_info>  selectByZWZt(Integer zw_zt,HttpServletRequest request,Integer pageNum,Integer pageSize);
	
	public void deleteByPrimaryKey (tb_gw_info gw);
	
	public tb_gw_info selectOne(String zw_id);
	
	public void update1(tb_gw_info gw);
	
	public void updateZt(String zw_id);

	public void updatefb(String zw_id);

	public Integer selectCountByQyId(String qy_id);

	public List<tb_gw_info> selectByQyId(String qy_id);

	PageInfo<Map<String, Object>> selectByQyIdquery(Integer pageNumber, Integer pageSize,String qy_id);

	public tb_gw_info selectByPrimaryKey(String zw_id);

	public gjss gjssss(gjss gjss);

	PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, gjss g);
	
	public PageInfo<gjss> gjrcZgjgw(Integer pageNum,Integer pageSize);
	public PageInfo<gjss> gjrcZgjgwAll(Integer pageNum,Integer pageSize);

	gjss gjssssforsj(gjss gjss);
}

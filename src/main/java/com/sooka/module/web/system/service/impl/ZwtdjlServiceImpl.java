package com.sooka.module.web.system.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.mapper.ZwtdMapper;
import com.sooka.mybatis.model.JlAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sooka.module.web.system.service.ZwtdjlService;
import com.sooka.module.web.system.vo.tb_zwtdjl;
import com.sooka.mybatis.mapper.QyInfoMapper;
import com.sooka.mybatis.mapper.ZwtdjlMapper;
@Service
public class ZwtdjlServiceImpl implements  ZwtdjlService{

	@Autowired 
	ZwtdjlMapper zwtdjlMapper;

	@Autowired
	ZwtdMapper zwtdMapper;

	@Autowired
	QyInfoMapper qyinfoMapper;

	public tb_zwtdjl selectJlAndMsBysessionId(String id){
		int tdjl_tdzt = zwtdjlMapper.selectCountByZh_id(id);
		int tdjl_mszt = zwtdjlMapper.selectCountTdztByZh_id(id);
		tb_qy_info selectQyQymcByZhId = qyinfoMapper.selectQyQymcByZhId(id);
		tb_zwtdjl tdjl = new tb_zwtdjl();
		tdjl.setTdjl_tdzt(tdjl_tdzt);
		tdjl.setTdjl_mszt(tdjl_mszt);
		tdjl.setZw_id(selectQyQymcByZhId.getQy_qymc());
		tdjl.setTdjl_time(selectQyQymcByZhId.getQy_file_logo());
		tdjl.setTdjl_id(selectQyQymcByZhId.getQy_id());
		return tdjl;
	}

	@Override
	public void updateMsyqByPrimiryKey(String tdjl_id) {
		tb_zwtdjl tdjl = new tb_zwtdjl();
		tdjl.setTdjl_id(tdjl_id);
		tdjl.setTdjl_tdzt(1);
		tdjl.setTdjl_mszt(0);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createdate = sdf.format(date);
		tdjl.setTdjl_yqsj(createdate);
		zwtdjlMapper.updateMsyqByPrimiryKeyForAll(tdjl);
	}

	@Override
	public void deleteByPrimiryKey(String tdjl_id) {
		// TODO Auto-generated method stub
		zwtdjlMapper.deleteByPrimiryKeyForTbZwtdjl(tdjl_id);
	}

	@Override
	public PageInfo<Map<String,Object>> page(Integer pageNumber, Integer pageSize, JlAll pojo) {
		PageHelper.startPage(pageNumber,pageSize);
			return new PageInfo<>(zwtdMapper.searchTalents(pojo));
	}

	@Override
	public PageInfo<Map<String,Object>> pageAll(Integer pageNumber, Integer pageSize, JlAll pojo) {
		PageHelper.startPage(pageNumber,pageSize);
		return new PageInfo<>(zwtdMapper.searchTalentsAll(pojo));
	}



//	@Override
//	public List<Map<String,Object>>searchTalents(String cshy, String nl, String nl1, String xb, String gznx, String gznx1, String xl, String xl1) {
//
//		return zwtdMapper.searchTalents(cshy,nl,nl1,xb,gznx,gznx1,xl,xl1);
//	}


}

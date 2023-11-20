package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.module.web.cms.service.GjrcService;
import com.sooka.module.web.cms.vo.GjrcVo;
import com.sooka.mybatis.mapper.GjrcMapper;

@Service
public class GjrcServiceImpl implements GjrcService {
	@Autowired
	private GjrcMapper gjrcMapper;
	@Override
	public String save(GjrcVo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(GjrcVo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GjrcVo findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GjrcVo> findList(GjrcVo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GjrcVo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<GjrcVo> page(Integer pageNumber, Integer pageSize,
			GjrcVo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<GjrcVo> page(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo<>(gjrcMapper.selectGjrc());
	}

}

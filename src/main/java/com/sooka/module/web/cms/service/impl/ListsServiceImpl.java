package com.sooka.module.web.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.module.web.cms.service.ListsService;
import com.sooka.module.web.system.vo.Lists;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.mybatis.mapper.ListsMapper;
import com.sooka.mybatis.mapper.QyInfoMapper;

@Service
public class ListsServiceImpl implements ListsService {
	@Autowired
	ListsMapper listMapper;
	@Autowired
	QyInfoMapper qyInfoMapper;

	@Override
	public String save(Lists pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Lists pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lists findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lists> findList(Lists pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lists> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Lists> page(Integer pageNumber, Integer pageSize, Lists pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Lists> page(Integer pageNumber, Integer pageSize,
			HttpServletRequest request, Integer type) {
		HttpSession session = request.getSession();
		tb_zh_info attribute = (tb_zh_info) session
				.getAttribute("tbZhInfo_loginSession");
		Example example = new Example(tb_qy_info.class);
		example.createCriteria().andCondition(
				"zh_id='" + attribute.getZh_id() + "'");
		tb_qy_info selectOneByExample = qyInfoMapper
				.selectOneByExample(example);
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo<>(listMapper.selectAllByQy_Id(
				selectOneByExample.getQy_id(), type));
	}

	@Override
	public PageInfo<Lists> page(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}

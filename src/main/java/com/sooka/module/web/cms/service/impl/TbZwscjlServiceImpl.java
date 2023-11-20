package com.sooka.module.web.cms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.Const;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.TbZwscjlService;
import com.sooka.module.web.system.vo.tb_jl_banben;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.module.web.system.vo.tb_zwscjl;
import com.sooka.mybatis.mapper.JlinfoMapper;
import com.sooka.mybatis.mapper.TbJlBanbenMapper;
import com.sooka.mybatis.mapper.TbZwscjlMapper;
import com.sooka.mybatis.model.Jlinfo;

@Service
public class TbZwscjlServiceImpl implements TbZwscjlService {
	@Autowired
	TbJlBanbenMapper banbenMapper;
	@Autowired
	TbZwscjlMapper zwscjlMapper;
	@Autowired
	JlinfoMapper jlinfoMapper;



	@Override
	public String save(tb_zwscjl pojo) {

		return null;
	}

	@Override
	public String update(tb_zwscjl pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public tb_zwscjl findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<tb_zwscjl> findList(tb_zwscjl pojo) {
		return zwscjlMapper.select(pojo);
	}

	@Override
	public List<tb_zwscjl> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<tb_zwscjl> page(Integer pageNumber, Integer pageSize,
			tb_zwscjl pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<tb_zwscjl> page(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(tb_zwscjl scjl, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info) session
				.getAttribute(Const.ZhInfoLoginSession);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createdate = sdf.format(date);
		scjl.setZwscjl_sj(createdate);
		scjl.setZwscjl_id(StrUtil.getUUID());
		scjl.setZh_id(user.getZh_id());
		Jlinfo selectOneByZhId = jlinfoMapper.findByUserCodeId(user.getZh_id());
		scjl.setJianli_id(selectOneByZhId.getJianliId());
		zwscjlMapper.insert(scjl);
	}

}

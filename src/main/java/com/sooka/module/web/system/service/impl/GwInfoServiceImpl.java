package com.sooka.module.web.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.system.service.GwInfoService;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.module.web.system.vo.tb_zwscjl;
import com.sooka.module.web.system.vo.tb_zwtdjl;
import com.sooka.mybatis.mapper.GwInfoMapper;
import com.sooka.mybatis.mapper.QyInfoMapper;
import com.sooka.mybatis.mapper.ZwscjlMapper;
import com.sooka.mybatis.mapper.ZwtdjlMapper;

@Service
public class GwInfoServiceImpl implements GwInfoService {

	@Autowired
	private GwInfoMapper infoMapper;
	@Autowired
	private QyInfoMapper qyInfoMapper;
	@Autowired
	private GwInfoMapper gwInfoMapper;

	@Autowired
	private ZwtdjlMapper tdjlMapper;
	
	@Autowired
	private ZwscjlMapper scjlMapper;


	@Override
	public void insertQyzxZjgw(tb_gw_info gw, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		tb_zh_info attribute = (tb_zh_info) session
				.getAttribute("tbZhInfo_loginSession");
		Example example = new Example(tb_qy_info.class);
		example.createCriteria().andCondition(
				"zh_id='" + attribute.getZh_id() + "'");
		tb_qy_info selectOneByExample = qyInfoMapper
				.selectOneByExample(example);
		gw.setZw_id(StrUtil.getUUID());
		gw.setQy_id(selectOneByExample.getQy_id());
		gw.setZw_sftj(0);
		gw.setZw_zt(0);
		gw.setZw_fbsc(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createdate = sdf.format(date);
		gw.setZw_fbsj(createdate);
		infoMapper.insertSelective(gw);
	}

	public PageInfo<tb_gw_info> selectByZWZt(Integer zw_zt,
			HttpServletRequest request, Integer pageNum,Integer pageSize) {
		Example example = new Example(tb_gw_info.class);
		Example example1 = new Example(tb_qy_info.class);
		HttpSession session = request.getSession();
		tb_zh_info attribute = (tb_zh_info) session
				.getAttribute("tbZhInfo_loginSession");

		example1.createCriteria().andCondition(
				"zh_id='" + attribute.getZh_id() + "'");

		tb_qy_info selectOneByExample = qyInfoMapper
				.selectOneByExample(example1);

		example.createCriteria().andCondition(
				"QY_ID= '" + selectOneByExample.getQy_id() + "' and ZW_ZT="
						+ zw_zt);
		PageHelper.startPage(pageNum, pageSize);

		return new PageInfo<>(infoMapper.selectByExample(example));
	}

	@Override
	public void deleteByPrimaryKey(tb_gw_info gw) {
		// TODO Auto-generated method stub
		Example example = new Example(tb_zwscjl.class);
		example.createCriteria().andCondition("ZW_ID='" + gw.getZw_id() + "'");
		scjlMapper.deleteByExample(example);

		Example example2 = new Example(tb_zwtdjl.class);
		example2.createCriteria().andCondition("ZW_ID='" + gw.getZw_id() + "'");
		tdjlMapper.deleteByExample(example2);

		gwInfoMapper.deleteById(gw.getZw_id());
	}

	@Override
	public tb_gw_info selectOne(String zw_id) {
		// TODO Auto-generated method stub
		Example example = new Example(tb_gw_info.class);
		example.createCriteria().andCondition("zw_id='" + zw_id + "'");
		tb_gw_info selectOneByExample = gwInfoMapper
				.selectOneByExample(example);
		return selectOneByExample;
	}

	@Override
	public void update1(tb_gw_info gw) {
		// TODO Auto-generated method stub
		Example example = new Example(tb_gw_info.class); 
		example.createCriteria().andCondition("zw_id='" + gw.getZw_id() + "'");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createdate = sdf.format(date);
		gw.setZw_gxrq(createdate);
		gwInfoMapper.updateByExampleSelective(gw, example);
	}

	@Override
	public void updateZt(String zw_id) {
		// TODO Auto-generated method stub
		tb_gw_info gw = new tb_gw_info();
		Example example = new Example(tb_gw_info.class);
		example.createCriteria().andCondition("zw_id='" + zw_id + "'");
		gw.setZw_zt(1);
		gwInfoMapper.updateByExampleSelective(gw, example);
	}

	public void updatefb(String zw_id) {
		tb_gw_info gw = new tb_gw_info();
		Example example = new Example(tb_gw_info.class);
		example.createCriteria().andCondition("zw_id='" + zw_id + "'");
		gw.setZw_zt(0);
		gwInfoMapper.updateByExampleSelective(gw, example);

	}

	@Override
	public Integer selectCountByQyId(String qy_id) {
		Example example = new Example(tb_gw_info.class);
		example.createCriteria().andCondition("qy_id='" + qy_id + "' and zw_zt=0");
		int selectCountByExample = gwInfoMapper.selectCountByExample(example);
		return selectCountByExample;
	}

	@Override
	public List<tb_gw_info> selectByQyId(String qy_id) {
		// TODO Auto-generated method stub
		/*Example example = new Example(tb_gw_info.class);
		example.createCriteria().andCondition("qy_id='" + qy_id + "' and zw_zt=0 ");
		return gwInfoMapper.selectByExample(example);*/
		return gwInfoMapper.selectByQyId(qy_id);
	}
	@Override
	public PageInfo<Map<String,Object>> selectByQyIdquery(Integer pageNumber, Integer pageSize,String qy_id) {
		PageHelper.startPage(pageNumber,pageSize);
		return new PageInfo<>(gwInfoMapper.selectByQyIdquery(qy_id));
	}


	@Override
	public tb_gw_info selectByPrimaryKey(String zw_id) {
		// TODO Auto-generated method stub
		return gwInfoMapper.selectZw_id(zw_id);
	}

	@Override
	public gjss gjssss(gjss gjss) {
		// TODO Auto-generated method stub
		// 判断在前台是否选择多个 如果选择多个的话分割字符串 前台用+ 号连接
		if(gjss.getZw_zwlb()!=null&&!"".equals(gjss.getZw_zwlb())){
			List<String> lb=new ArrayList<String>();
			if (gjss.getZw_zwlb().indexOf("+") != -1) {
				String[] aa = gjss.getZw_zwlb().split("[+]");
				String u = "";
				for (String string : aa) {
					lb.add(string);
					//u = u + "\"" + string + "\"" + ",";
				}
				//u = u.substring(0, u.length() - 1);
				gjss.setZw_zwlb(u);
			}else{
				lb.add(gjss.getZw_zwlb());
			}
			gjss.setZwlb(lb);
		}
		if(gjss.getQy_sshy()!=null&&!"".equals(gjss.getQy_sshy())){
			List<String> hy=new ArrayList<String>();
			if (gjss.getQy_sshy().indexOf("+") != -1) {
				String[] aa = gjss.getQy_sshy().split("[+]");
				String u = "";
				for (String string : aa) {
					hy.add(string);
					//u = u + "\"" + string + "\"" + ",";
				}
				//u = u.substring(0, u.length() - 1);
				gjss.setQy_sshy(u);
			}else{
				hy.add(gjss.getQy_sshy());
			}
			gjss.setSshy(hy);
		}
		if(gjss.getFbrq()!=null&&gjss.getFbrq()!=-1){
			if(gjss.getFbrq()==0){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-1))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}else if(gjss.getFbrq()==1){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-3))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}else if(gjss.getFbrq()==2){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-7))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}else if(gjss.getFbrq()==3){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-30))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}
			
		}
		return gjss;
	}

	@Override
	public gjss gjssssforsj(gjss gjss) {
		// TODO Auto-generated method stub
		// 判断在前台是否选择多个 如果选择多个的话分割字符串 前台用+ 号连接
		if(gjss.getZw_zwlb()!=null&&!"".equals(gjss.getZw_zwlb())){
			List<String> lb=new ArrayList<String>();
			if (gjss.getZw_zwlb().indexOf(",") != -1) {
				String[] aa = gjss.getZw_zwlb().split(",");
				String u = "";
				for (String string : aa) {
					lb.add(string);
					//u = u + "\"" + string + "\"" + ",";
				}
				//u = u.substring(0, u.length() - 1);
				gjss.setZw_zwlb(u);
			}else{
				lb.add(gjss.getZw_zwlb());
			}
			gjss.setZwlb(lb);
		}
		if(gjss.getQy_sshy()!=null&&!"".equals(gjss.getQy_sshy())){
			List<String> hy=new ArrayList<String>();
			if (gjss.getQy_sshy().indexOf(",") != -1) {
				String[] aa = gjss.getQy_sshy().split(",");
				String u = "";
				for (String string : aa) {
					hy.add(string);
					//u = u + "\"" + string + "\"" + ",";
				}
				//u = u.substring(0, u.length() - 1);
				gjss.setQy_sshy(u);
			}else{
				hy.add(gjss.getQy_sshy());
			}
			gjss.setSshy(hy);
		}
		if(gjss.getFbrq()!=null&&gjss.getFbrq()!=-1){
			if(gjss.getFbrq()==0){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-1))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}else if(gjss.getFbrq()==1){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-3))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}else if(gjss.getFbrq()==2){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-7))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}else if(gjss.getFbrq()==3){
				gjss.setStartDate(DateUtil.formatDate((DateUtil.getOffsiteDate(new Date(),Calendar.DATE,-30))));
				gjss.setEndDate(DateUtil.formatDate(new Date()));
			}
			
		}
		return gjss;
	}
	public String save(tb_gw_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(tb_gw_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public tb_gw_info findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<tb_gw_info> findList(tb_gw_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<tb_gw_info> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Map<String,Object>> page(Integer pageNumber, Integer pageSize, gjss pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(gwInfoMapper.selectExampleGjss(pojo));
	}

	@Override
	public PageInfo<tb_gw_info> page(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageInfo<tb_gw_info> page(Integer pageNumber, Integer pageSize, tb_gw_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<gjss> gjrcZgjgw(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(gwInfoMapper.gjrcZgjgw());
	}
	@Override
	public PageInfo<gjss> gjrcZgjgwAll(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(gwInfoMapper.gjrcZgjgwAll());
	}

}

package com.sooka.module.web.system.service.impl;




import java.util.Date;
import java.util.List;

import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.JsonUtil;
import com.sooka.mybatis.mapper.TCmsEnterpriseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.system.service.QyInfoService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.mapper.QyInfoMapper;
import com.sooka.mybatis.mapper.TbzhInfoMapper;
@Service
public class QyInfoServiceImpl implements   QyInfoService{

	@Autowired QyInfoMapper qyInfoMapper;
	@Autowired TbzhInfoMapper infoMapper;
	@Autowired
	private TCmsEnterpriseMapper enterpriseMapper;
	@Override
	public tb_qy_info selectByzhInfoId(String zh_id) {
		Example example = new Example(tb_qy_info.class);
		example.createCriteria().andCondition("zh_id='" + zh_id + "'");
		tb_qy_info selectOneByExample = qyInfoMapper
				.selectOneByExample(example);
		return selectOneByExample;
	}

	@Override
	public tb_qy_info selectAll(String zh_id) {
		return  qyInfoMapper.selectByzhInfoId(zh_id);
	}
	@Override
	public tb_qy_info selectByzhId(String zhId) {
		return qyInfoMapper.selectByzhId(zhId);
	}
	@Override
	public boolean UpdateQyxx(tb_qy_info qy) {
		// TODO Auto-generated method stub

		Example example=new Example(tb_qy_info.class);
		example.createCriteria().andCondition("zh_id='"+qy.getZh_id()+"'");
		tb_qy_info selectOneByExample = qyInfoMapper
				.selectOneByExample(example);
		if(!StrUtil.checkObjAllFieldsIsNull(selectOneByExample)){
			qy.setQy_spzt(0);
			qyInfoMapper.updateByExampleSelective(qy, example);
			//enterpriseMapper.updateByPrimaryKeySelective(qy);
			return true;
		}else {
			qy.setQy_id(StrUtil.getUUID());
			qy.setQy_spzt(0);
			qy.setQy_sftj("0");
			qy.setQy_fbsc(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			if(StringUtils.isEmpty(qy.getQy_file_logo())){
				qy.setQy_file_logo("");
			}
			qyInfoMapper.insertSelective(qy);
			//enterpriseMapper.insertSelective(qy);
			return true;
		}

	}
	@Override
	public List<tb_qy_info> selectQyInfoForMq(Integer qy_sftj, Integer size) {
		
		return qyInfoMapper.selectMq(qy_sftj, size);
	}

	@Override
	public tb_qy_info selectByPrimaryKey(String qy_id) {
		// TODO Auto-generated method stub
		Example example = new Example(tb_qy_info.class);
		example.createCriteria().andCondition("qy_id='" + qy_id + "'");
		tb_qy_info selectByPrimaryKey = qyInfoMapper
				.selectOneByExample(example);
		return selectByPrimaryKey;
	}
	@Override
	public String save(tb_qy_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String update(tb_qy_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public tb_qy_info findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<tb_qy_info> findList(tb_qy_info pojo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<tb_qy_info> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageInfo<tb_qy_info> page(Integer pageNumber, Integer pageSize, tb_qy_info pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(qyInfoMapper.select(pojo));
	}
	@Override
	public PageInfo<tb_qy_info> page(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	public void deleteRelationByQyId(String qyId){
		qyInfoMapper.deleteTdjlByQyId(qyId);
		qyInfoMapper.deleteByZwscByQyId(qyId);
		qyInfoMapper.deleteByJllljlByQyId(qyId);
		qyInfoMapper.deleteGwByQyId(qyId);
		//qyInfoMapper.deleteByPrimaryKey(qyId);
	}

}

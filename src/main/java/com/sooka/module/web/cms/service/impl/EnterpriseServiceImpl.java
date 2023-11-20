package com.sooka.module.web.cms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sooka.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.EnterpriseService;
import com.sooka.module.web.system.service.QyInfoService;
import com.sooka.module.web.system.service.SysUserService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.mybatis.mapper.QyInfoMapper;
import com.sooka.mybatis.mapper.TCmsEnterpriseMapper;
import com.sooka.mybatis.mapper.TbzhInfoMapper;


@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private TCmsEnterpriseMapper enterpriseMapper;

	@Autowired
	QyInfoMapper qyInfoMapper;
	@Autowired
	QyInfoService qyInfoService;
	@Autowired
	private SysUserService userService;
	@Autowired
	private TbzhInfoMapper infoMapper;
	@Override
	public String save(tb_qy_info pojo) {
		pojo.setQy_id(StrUtil.getUUID());
		pojo.setQy_spzt(0);
		pojo.setQy_sftj("0");
		pojo.setQy_fbsc(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		if(enterpriseMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","enterprise-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(tb_qy_info pojo) {
		 if(enterpriseMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","enterprise-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	enterpriseMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","enterprise-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public String deleteByGwId(String[] ids) {
		if(ids!=null&&ids.length>0){
			for(String id:ids){
				enterpriseMapper.deleteByPrimaryKey(id);
				qyInfoService.deleteRelationByQyId(id);
				
			}
			return JsonUtil.toSUCCESS("删除成功!","enterprise-tab",true);
		}
		return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public tb_qy_info findById(Integer id) {
		return enterpriseMapper.selectByPrimaryKey(id);
	}

	@Override
	public tb_qy_info findByQyId(String qy_id) {
		return enterpriseMapper.selectByPrimaryKey(qy_id);
}

	@Override
	public List<tb_qy_info> findList(tb_qy_info pojo) {
		return enterpriseMapper.select(pojo);
	}

	@Override
	public List<tb_qy_info> findAll() {
		return enterpriseMapper.selectAll();
	}

	@Override
	public PageInfo<tb_qy_info> page(Integer pageNumber, Integer pageSize, tb_qy_info pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(qyInfoMapper.selectQy(pojo));
	}

	@Override
	public PageInfo<tb_qy_info> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public List<tb_qy_info> selectFamousEnterprise(Integer isFamous, Integer size) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("isFamous", isFamous);
		map.put("size", size);
		return enterpriseMapper.selectFamousEnterprise(map);
	}


	@Override
	public String approval(tb_qy_info enterprise) {
		enterprise.setQy_spzt(1);
		if(enterpriseMapper.updateByPrimaryKeySelective(enterprise)>0){
			return JsonUtil.toSUCCESS("操作成功!","enterprise-tab",false);
		}
		return JsonUtil.toERROR("操作失败!");
	}

	@Override
	public String updateSftj(tb_qy_info enterprise,Integer type) {
		if(type==0){
			enterprise.setQy_sftj("0");
		}else if(type==1){
			enterprise.setQy_sftj("1");
		}else if(type==2){
			enterprise.setQy_sftj("2");
		}
		if(enterpriseMapper.updateByPrimaryKeySelective(enterprise)>0){
			return JsonUtil.toSUCCESS("操作成功!","enterprise-tab",true);
		}
		return JsonUtil.toERROR("操作失败!");
	}
}

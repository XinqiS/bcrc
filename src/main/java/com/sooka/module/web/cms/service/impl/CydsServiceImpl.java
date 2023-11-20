package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.CydsService;
import com.sooka.mybatis.mapper.CydsMapper;
import com.sooka.mybatis.model.Cyds;


@Service
public class CydsServiceImpl implements CydsService {

    @Autowired
    private CydsMapper cydsMapper;

	@Override
	public String save(Cyds pojo) {
		pojo.setCydsId(UuidUtil.get32UUID());
		if(cydsMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","cyds-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Cyds pojo) {
		 if(cydsMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","cyds-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	cydsMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Cyds findById(Integer id) {
		return cydsMapper.selectByPrimaryKey(id);
	}*/

	@Override
	public List<Cyds> findList(Cyds pojo) {
		return cydsMapper.select(pojo);
	}

	@Override
	public List<Cyds> findAll() {
		return cydsMapper.selectAll();
	}

	@Override
	public PageInfo<Cyds> page(Integer pageNumber, Integer pageSize, Cyds pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(cydsMapper.selectAll());
	}

	@Override
	public PageInfo<Cyds> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	cydsMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","cyds-tab",false);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Cyds findById(String id) {
		return cydsMapper.selectByPrimaryKey(id);
	}
  
}

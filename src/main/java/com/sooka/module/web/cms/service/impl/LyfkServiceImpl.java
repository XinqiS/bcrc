package com.sooka.module.web.cms.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.LyfkService;
import com.sooka.mybatis.mapper.LyfkMapper;
import com.sooka.mybatis.model.Lyfk;


@Service
public class LyfkServiceImpl implements LyfkService {

    @Autowired
    private LyfkMapper lyfkMapper;

	@Override
	public String save(Lyfk pojo) {
		pojo.setLyfkId(UuidUtil.get32UUID());
		if(lyfkMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","lyfk-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Lyfk pojo) {
		
		 if(lyfkMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","lyfk-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	lyfkMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Lyfk findById(Integer id) {
		return lyfkMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Lyfk> findList(Lyfk pojo) {
		return lyfkMapper.select(pojo);
	}

	@Override
	public List<Lyfk> findAll() {
		return lyfkMapper.selectAll();
	}

	@Override
	public PageInfo<Lyfk> page(Integer pageNumber, Integer pageSize, Lyfk pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(lyfkMapper.selectAll());
	}

	@Override
	public PageInfo<Lyfk> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	lyfkMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","lyfk-tab",false);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Lyfk findById(String id) {
		return lyfkMapper.selectByPrimaryKey(id);
	}
  
}

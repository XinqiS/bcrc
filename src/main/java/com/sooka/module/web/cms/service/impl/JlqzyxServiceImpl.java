package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JlqzyxService;
import com.sooka.mybatis.mapper.JlqzyxMapper;
import com.sooka.mybatis.model.Jlqzyx;


@Service
public class JlqzyxServiceImpl implements JlqzyxService {

    @Autowired
    private JlqzyxMapper jlqzyxMapper;

	@Override
	public String save(Jlqzyx pojo) {
		pojo.setQzyxId(UuidUtil.get32UUID());
		if(jlqzyxMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jlqzyx pojo) {
		
		 if(jlqzyxMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	jlqzyxMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlqzyx findById(Integer id) {
		return jlqzyxMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Jlqzyx> findList(Jlqzyx pojo) {
		return jlqzyxMapper.select(pojo);
	}

	@Override
	public List<Jlqzyx> findAll() {
		return jlqzyxMapper.selectAll();
	}

	@Override
	public PageInfo<Jlqzyx> page(Integer pageNumber, Integer pageSize, Jlqzyx pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jlqzyxMapper.selectAll());
	}

	@Override
	public PageInfo<Jlqzyx> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jlqzyxMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlqzyx findById(String id) {
		return jlqzyxMapper.selectByPrimaryKey(id);
	}

	@Override
	public Jlqzyx findJlqzyxByJlBanbenId(String id) {
		
		return jlqzyxMapper.findJlqzyxByJlBanbenId(id);
	}

	@Override
	public void deleteJlqzyxByJlBanbenId(String string) {
		jlqzyxMapper.deleteJlqzyxByJlBanbenId(string);
		
	}

  
}

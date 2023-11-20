package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JljyjlService;
import com.sooka.mybatis.mapper.JljyjlMapper;
import com.sooka.mybatis.model.Jljyjl;


@Service
public class JljyjlServiceImpl implements JljyjlService {

    @Autowired
    private JljyjlMapper jljyjlMapper;

	@Override
	public String save(Jljyjl pojo) {
		pojo.setJyjlId(UuidUtil.get32UUID());
		if(jljyjlMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jljyjl pojo) {
		
		 if(jljyjlMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	jljyjlMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jljyjl findById(Integer id) {
		return jljyjlMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Jljyjl> findList(Jljyjl pojo) {
		return jljyjlMapper.select(pojo);
	}

	@Override
	public List<Jljyjl> findAll() {
		return jljyjlMapper.selectAll();
	}

	@Override
	public PageInfo<Jljyjl> page(Integer pageNumber, Integer pageSize, Jljyjl pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jljyjlMapper.selectAll());
	}

	@Override
	public PageInfo<Jljyjl> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jljyjlMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jljyjl findById(String id) {
		return jljyjlMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Jljyjl> findJyjlByJibanbenId(String JL_BB_ID) {
		return jljyjlMapper.findJyjlByJibanbenId(JL_BB_ID);
	}

	@Override
	public List<Jljyjl> findByJibanbenId(String JL_BB_ID) {
		return jljyjlMapper.findByJibanbenId(JL_BB_ID);
	}

	@Override
	public void deleteJyjlByJibanbenId(String string) {
		jljyjlMapper.deleteJyjlByJibanbenId(string);
		
	}
  
}

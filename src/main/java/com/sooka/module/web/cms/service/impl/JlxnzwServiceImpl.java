package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JlxnzwService;
import com.sooka.mybatis.mapper.JlxnzwMapper;
import com.sooka.mybatis.model.Jlxnzw;


@Service
public class JlxnzwServiceImpl implements JlxnzwService {

    @Autowired
    private JlxnzwMapper jlxnzwMapper;

	@Override
	public String save(Jlxnzw pojo) {
		pojo.setXnzwId(UuidUtil.get32UUID());
		if(jlxnzwMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jlxnzw pojo) {
		
		 if(jlxnzwMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	jlxnzwMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlxnzw findById(Integer id) {
		return jlxnzwMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Jlxnzw> findList(Jlxnzw pojo) {
		return jlxnzwMapper.select(pojo);
	}

	@Override
	public List<Jlxnzw> findAll() {
		return jlxnzwMapper.selectAll();
	}

	@Override
	public PageInfo<Jlxnzw> page(Integer pageNumber, Integer pageSize, Jlxnzw pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jlxnzwMapper.selectAll());
	}

	@Override
	public PageInfo<Jlxnzw> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jlxnzwMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlxnzw findById(String id) {
		return jlxnzwMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Jlxnzw> findXnzwByJibanbenId(String JL_BB_ID) {
		return jlxnzwMapper.findXnzwByJibanbenId(JL_BB_ID);
	}

	@Override
	public void deleteXnzwByJibanbenId(String string) {
		jlxnzwMapper.deleteXnzwByJibanbenId(string);
		
	}
  
}

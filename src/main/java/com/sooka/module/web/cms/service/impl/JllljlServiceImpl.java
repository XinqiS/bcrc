package com.sooka.module.web.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JllljlService;
import com.sooka.mybatis.mapper.JllljlMapper;
import com.sooka.mybatis.model.Jllljl;


@Service
public class JllljlServiceImpl implements JllljlService {

    @Autowired
    private JllljlMapper jllljlMapper;

	@Override
	public String save(Jllljl pojo) {
		pojo.setJlllId(UuidUtil.get32UUID());
		if(jllljlMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jllljl pojo) {
		
		 if(jllljlMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	jllljlMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jllljl findById(Integer id) {
		return jllljlMapper.selectByPrimaryKey(id);
	}
*/
	public Jllljl findById(String id) {
		return jllljlMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Jllljl> findList(Jllljl pojo) {
		return jllljlMapper.select(pojo);
	}

	@Override
	public List<Jllljl> findAll() {
		return jllljlMapper.selectAll();
	}

	@Override
	public PageInfo<Jllljl> page(Integer pageNumber, Integer pageSize, Jllljl pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jllljlMapper.selectAll());
	}

	@Override
	public PageInfo<Jllljl> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jllljlMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, Map<String,Object> map) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jllljlMapper.findJllljlByZhId(map));
	}

	@Override
	public List<Map<String, Object>> findJllljlByZhId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findSkgwCountByZhId(String zh_id) {
		return jllljlMapper.findSkgwCountByZhId(zh_id);
	}

	@Override
	public void deleteJllljlByJibanbenId(String string) {
		jllljlMapper.deleteJllljlByJibanbenId(string);
		
	}

	
  
}

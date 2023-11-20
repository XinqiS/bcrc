package com.sooka.module.web.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.ZwtdService;
import com.sooka.mybatis.mapper.ZwtdMapper;
import com.sooka.mybatis.model.Zwtdjl;


@Service
public class ZwtdServiceImpl implements ZwtdService {

    @Autowired
    private ZwtdMapper zwtdMapper;

	@Override
	public String save(Zwtdjl pojo) {
		pojo.setTdjlId(UuidUtil.get32UUID());
		if(zwtdMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Zwtdjl pojo) {
		
		 if(zwtdMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	zwtdMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Zwtdjl findById(Integer id) {
		return zwtdMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Zwtdjl> findList(Zwtdjl pojo) {
		return zwtdMapper.select(pojo);
	}

	@Override
	public List<Zwtdjl> findAll() {
		return zwtdMapper.selectAll();
	}

	@Override
	public PageInfo<Zwtdjl> page(Integer pageNumber, Integer pageSize, Zwtdjl pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(zwtdMapper.selectAll());
	}

	@Override
	public PageInfo<Zwtdjl> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	zwtdMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Zwtdjl findById(String id) {
		return zwtdMapper.selectByPrimaryKey(id);
	}
	@Override
	public PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, Map<String,Object> map) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(zwtdMapper.findZwtdjlByZhId(map));
	}

	@Override
	public int findWdsqCountByZhId(String zh_id) {
		return zwtdMapper.findWdsqCountByZhId(zh_id);
	}

	@Override
	public void deleteZwtdjlByJibanbenId(String string) {
		zwtdMapper.deleteZwtdjlByJibanbenId(string);
		
	}
	public void deleteZhiId(String zhiId){
		zwtdMapper.deleteZhiId(zhiId);
	}
	@Override
	public PageInfo<Map<String, Object>> pageAll(Integer pageNumber, Integer pageSize, Map<String,Object> map) {
		PageHelper.startPage(pageNumber,pageSize);
		return new PageInfo<>(zwtdMapper.findZwtdjlByZhIdAll());
	}

	@Override
	public List<Map<String,Object>> findZwtdjlByZhIdquery(Map<String,Object> map){

		return zwtdMapper.findZwtdjlByZhId(map);
	}

}

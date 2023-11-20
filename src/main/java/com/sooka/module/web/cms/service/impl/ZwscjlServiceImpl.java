package com.sooka.module.web.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.ZwscjlService;
import com.sooka.mybatis.mapper.ZwscjlMapper;
import com.sooka.mybatis.model.Zwscjl;


@Service
public class ZwscjlServiceImpl implements ZwscjlService {

    @Autowired
    private ZwscjlMapper zwscjlMapper;

	@Override
	public String save(Zwscjl pojo) {
		pojo.setZwscjlId(UuidUtil.get32UUID());
		if(zwscjlMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Zwscjl pojo) {
		
		 if(zwscjlMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	zwscjlMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Zwscjl findById(Integer id) {
		return zwscjlMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Zwscjl> findList(Zwscjl pojo) {
		return zwscjlMapper.select(pojo);
	}

	@Override
	public List<Zwscjl> findAll() {
		return zwscjlMapper.selectAll();
	}

	@Override
	public PageInfo<Zwscjl> page(Integer pageNumber, Integer pageSize, Zwscjl pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(zwscjlMapper.selectAll());
	}

	@Override
	public PageInfo<Zwscjl> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	zwscjlMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Zwscjl findById(String id) {
		return zwscjlMapper.selectByPrimaryKey(id);
	}
	@Override
	public PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, Map<String,Object> map) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(zwscjlMapper.findZwscjlByZhId(map));
	}

	@Override
	public int findWdscCountByZhId(String zh_id) {
		return zwscjlMapper.findWdscCountByZhId(zh_id);
	}

	@Override
	public List<Map<String,Object>> findZwscjlByZhIdquery(Map<String,Object> map){
		return zwscjlMapper.findZwscjlByZhId(map);
	}
	@Override
	public void deleteZhiId(String zhiId){
		zwscjlMapper.deleteZhiId(zhiId);
	}
}

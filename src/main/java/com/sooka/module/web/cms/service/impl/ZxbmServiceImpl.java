package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.ZxbmService;
import com.sooka.mybatis.mapper.ZxbmMapper;
import com.sooka.mybatis.model.Zxbm;


@Service
public class ZxbmServiceImpl implements ZxbmService {

    @Autowired
    private ZxbmMapper zxbmMapper;

	@Override
	public String save(Zxbm pojo) {
		pojo.setZxbmId(UuidUtil.get32UUID());
		if(zxbmMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","zxbm-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Zxbm pojo) {
		 if(zxbmMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","zxbm-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	zxbmMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Zxbm findById(Integer id) {
		return zxbmMapper.selectByPrimaryKey(id);
	}*/

	@Override
	public List<Zxbm> findList(Zxbm pojo) {
		return zxbmMapper.select(pojo);
	}

	@Override
	public List<Zxbm> findAll() {
		return zxbmMapper.selectAll();
	}

	@Override
	public PageInfo<Zxbm> page(Integer pageNumber, Integer pageSize, Zxbm pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(zxbmMapper.selectAll());
	}

	@Override
	public PageInfo<Zxbm> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	zxbmMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","zxbm-tab",false);
        }
        return JsonUtil.toERROR("删除失败!");
	}
	@Override
	public Zxbm findById(String id) {
		return zxbmMapper.selectByPrimaryKey(id);
	}
}

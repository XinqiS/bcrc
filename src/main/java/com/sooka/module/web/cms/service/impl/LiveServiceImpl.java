package com.sooka.module.web.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.LiveService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.mapper.TCmsLiveMapper;
import com.sooka.mybatis.model.TCmsLive;


@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private TCmsLiveMapper liveMapper;

	@Override
	public String save(TCmsLive pojo) {
		pojo.setSpeakDate(new Date());
		if(liveMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(TCmsLive pojo) {
		 if(liveMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
                liveMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除留言成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除留言失败!");
	}

	@Override
	public TCmsLive findById(Integer id) {
		return liveMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TCmsLive> findList(TCmsLive pojo) {
		return liveMapper.select(pojo);
	}

	@Override
	public List<TCmsLive> findAll() {
		return liveMapper.selectAll();
	}

	@Override
	public PageInfo<TCmsLive> page(Integer pageNumber, Integer pageSize, TCmsLiveVo pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(liveMapper.selectByCondition(pojo));
	}

	@Override
	public PageInfo<TCmsLive> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public PageInfo<TCmsLive> page(Integer pageNumber, Integer pageSize, TCmsLive pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer selectMaxSort(TCmsLive live) {
		return liveMapper.selectMaxSort(live);
	}
	public List<TCmsLive> selectForTag(TCmsLive live) {
		return liveMapper.selectForTag(live);
	}

	@Override
	public List<TCmsLive> selectByCondition(TCmsLive live) {
		// TODO Auto-generated method stub
		return null;
	}
  
}

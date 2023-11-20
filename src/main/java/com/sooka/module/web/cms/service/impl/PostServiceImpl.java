package com.sooka.module.web.cms.service.impl;

import java.util.Date;
import java.util.List;

import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.module.web.system.vo.tb_qy_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.LiveService;
import com.sooka.module.web.cms.service.PostService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.mapper.TCmsLiveMapper;
import com.sooka.mybatis.mapper.TCmsPostMapper;
import com.sooka.mybatis.model.TCmsLive;
import com.sooka.mybatis.model.TCmsPost;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private TCmsPostMapper postMapper;

	@Override
	public String save(tb_gw_info pojo) {
		pojo.setZw_id(StrUtil.getUUID());
		pojo.setZw_sftj(0);
		pojo.setZw_zt(0);
		pojo.setZw_fbsc(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		if(postMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","enterprise-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(tb_gw_info pojo) {
		 if(postMapper.updateByZwId(pojo)){
	            return JsonUtil.toSUCCESS("操作成功!","enterprise-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	postMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public tb_gw_info findById(Integer id) {
		return postMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<tb_gw_info> findList(tb_gw_info pojo) {
		return postMapper.select(pojo);
	}

	@Override
	public List<tb_gw_info> findAll() {
		return postMapper.selectAll();
	}

	@Override
	public PageInfo<tb_gw_info> page(Integer pageNumber, Integer pageSize, tb_gw_info pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(postMapper.selectPostByGwId(pojo));
	}

	@Override
	public PageInfo<tb_gw_info> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public List<tb_gw_info> selectPostByEnterpriseId(tb_gw_info post) {
		
		return null;
	}

	@Override
	public String deleteByZwId(String[] ids) {
		if(ids!=null&&ids.length>0){
			for(String zw_id:ids){
				postMapper.deleteByZwId(zw_id);
			}
			return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
		}
		return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public String updateSftj(tb_gw_info post,Integer type) {
		if(type==0){
			post.setZw_sftj(0);
		}else if(type==1){
			post.setZw_sftj(1);
		}
		if(postMapper.updateZwSftj(post)){
			return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
		}
		return JsonUtil.toERROR("操作失败!");
	}
}

package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JlinfoService;
import com.sooka.mybatis.mapper.JlinfoMapper;
import com.sooka.mybatis.model.Jlinfo;


@Service
public class JlinfoServiceImpl implements JlinfoService {

    @Autowired
    private JlinfoMapper jlinfoMapper;

	@Override
	public String save(Jlinfo pojo) {
		pojo.setJianliId(UuidUtil.get32UUID());
		if(jlinfoMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","accountNumber",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jlinfo pojo) {
		
		 if(jlinfoMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","accountNumber",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	jlinfoMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlinfo findById(Integer id) {
		return jlinfoMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Jlinfo> findList(Jlinfo pojo) {
		return jlinfoMapper.select(pojo);
	}

	@Override
	public List<Jlinfo> findAll() {
		return jlinfoMapper.selectAll();
	}

	@Override
	public PageInfo<Jlinfo> page(Integer pageNumber, Integer pageSize, Jlinfo pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jlinfoMapper.selectAll());
	}

	@Override
	public PageInfo<Jlinfo> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jlinfoMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlinfo findById(String id) {
		return jlinfoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Jlinfo findByUserCodeId(String id) {
		return jlinfoMapper.findByUserCodeId(id);
	}

	@Override
	public void deleteByUserCodeId(String zh_id) {
		// TODO Auto-generated method stub
		jlinfoMapper.deleteZhiId(zh_id);
	}
  
}

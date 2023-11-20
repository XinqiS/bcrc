package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JlgzjyService;
import com.sooka.mybatis.mapper.JlgzjyMapper;
import com.sooka.mybatis.model.Jlgzjy;


@Service
public class JlgzjyServiceImpl implements JlgzjyService {

    @Autowired
    private JlgzjyMapper jlgzjyMapper;

	@Override
	public String save(Jlgzjy pojo) {
		pojo.setGzjyId(UuidUtil.get32UUID());
		if(jlgzjyMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jlgzjy pojo) {
		
		 if(jlgzjyMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	jlgzjyMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlgzjy findById(Integer id) {
		return jlgzjyMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Jlgzjy> findList(Jlgzjy pojo) {
		return jlgzjyMapper.select(pojo);
	}

	@Override
	public List<Jlgzjy> findAll() {
		return jlgzjyMapper.selectAll();
	}

	@Override
	public PageInfo<Jlgzjy> page(Integer pageNumber, Integer pageSize, Jlgzjy pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jlgzjyMapper.selectAll());
	}

	@Override
	public PageInfo<Jlgzjy> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jlgzjyMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlgzjy findById(String id) {
		return jlgzjyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Jlgzjy> findGzjyByJibanbenId(String JL_BB_ID) {
		return jlgzjyMapper.findGzjyByJibanbenId(JL_BB_ID);
	}

	@Override
	public List<Jlgzjy> findByJibanbenId(String JL_BB_ID) {
		return jlgzjyMapper.findByJibanbenId(JL_BB_ID);
	}


	@Override
	public void deleteGzjyByJibanbenId(String string) {
		jlgzjyMapper.deleteGzjyByJibanbenId(string);
		
	}
  
}

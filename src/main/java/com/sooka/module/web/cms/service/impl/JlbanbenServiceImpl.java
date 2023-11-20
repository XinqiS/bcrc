package com.sooka.module.web.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.UuidUtil;
import com.sooka.module.web.cms.service.JlbanbenService;
import com.sooka.mybatis.mapper.JlbanbenMapper;
import com.sooka.mybatis.model.Jlbanben;


@Service
public class JlbanbenServiceImpl implements JlbanbenService {

    @Autowired
    private JlbanbenMapper jlbanbenMapper;

	@Override
	public String save(Jlbanben pojo) {
		pojo.setJlBbId(UuidUtil.get32UUID());
		if(jlbanbenMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","content-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
	}

	@Override
	public String update(Jlbanben pojo) {
		
		 if(jlbanbenMapper.updateByPrimaryKeySelective(pojo)>0){
	            return JsonUtil.toSUCCESS("操作成功!","content-tab",true);
	     }
	     return JsonUtil.toERROR("操作失败!");
	}

	/*@Override
	public String delete(Integer[] ids) {
		if(ids!=null&&ids.length>0){
            for(Integer id:ids){
            	JlbanbenMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}

	@Override
	public Jlbanben findById(Integer id) {
		return JlbanbenMapper.selectByPrimaryKey(id);
	}
*/
	@Override
	public List<Jlbanben> findList(Jlbanben pojo) {
		return jlbanbenMapper.select(pojo);
	}

	@Override
	public List<Jlbanben> findAll() {
		return jlbanbenMapper.selectAll();
	}

	@Override
	public PageInfo<Jlbanben> page(Integer pageNumber, Integer pageSize, Jlbanben pojo) {
		PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(jlbanbenMapper.select(pojo));
	}

	@Override
	public PageInfo<Jlbanben> page(Integer pageNumber, Integer pageSize) {
		 PageHelper.startPage(pageNumber,pageSize);
	     return new PageInfo<>(findAll());
	}

	@Override
	public String delete(String[] ids) {
		if(ids!=null&&ids.length>0){
            for(String id:ids){
            	jlbanbenMapper.deleteByPrimaryKey(id);
            }
            return JsonUtil.toSUCCESS("删除成功!","content-tab",true);
        }
        return JsonUtil.toERROR("删除失败!");
	}
	@Override
	public void  deletejlBBid(String id){
		jlbanbenMapper.deleteByPrimaryKey(id);
	}
	@Override
	public Jlbanben findById(String id) {
		return jlbanbenMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateJlbanbenMRtdzt(String jianliId) {
		jlbanbenMapper.updateJlbanbenMRtdzt(jianliId);
		
	}

	@Override
	public String delete(String zh_id, Integer zh_sf) {
		try{
			if(zh_sf==0){
				jlbanbenMapper.del_grrelation(zh_id);
			}else{
				jlbanbenMapper.del_qyrelation(zh_id);
			}
			return JsonUtil.toSUCCESS("删除成功!","content-tab",false);
		}catch(Exception e){
			return JsonUtil.toSUCCESS(e.toString(),"content-tab",false);
		}
	}
  
}

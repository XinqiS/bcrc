package com.sooka.module.web.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.PcService;
import com.sooka.mybatis.mapper.TCmsPcMapper;
import com.sooka.mybatis.model.TCmsPc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PcServiceImpl implements PcService {

    @Autowired
    private TCmsPcMapper pcMapper;



    @Override
    public String save(TCmsPc pojo) {
        pojo.setCreatetime(new Date());
        if(pcMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","att-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
    }

    @Override
    public String update(TCmsPc pojo) {
        pojo.setCreatetime(new Date());
        if(pcMapper.updateByPrimaryKeySelective(pojo)>0){
            return JsonUtil.toSUCCESS("操作成功!","att-tab",true);
        }
        return JsonUtil.toERROR("操作失败!");
    }

    @Override
    public String delete(Integer[] ids) {
        if(ids!=null&&ids.length>0){
            for(Integer id:ids){
                pcMapper.deleteByPrimaryKey(id);
                return JsonUtil.toSUCCESS("删除成功!","att-tab",false);
            }
        }
        return JsonUtil.toERROR("删除失败!");
    }

    @Override
    public TCmsPc findById(Integer id) {
        return pcMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TCmsPc> findList(TCmsPc pojo) {
        return pcMapper.select(pojo);
    }

    @Override
    public List<TCmsPc> findAll() {
        return pcMapper.selectAll();
    }

    @Override
    public PageInfo<TCmsPc> page(Integer pageNumber, Integer pageSize, TCmsPc pojo) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(findAll());
    }



    @Override
    public PageInfo<TCmsPc> page(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public PageInfo<TCmsPc> pagequery(Integer pageNumber, Integer pageSize,TCmsPc pojo) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(selectPc());
    }

    @Override
    public  List<TCmsPc> selectPc() {
        return pcMapper.selectPc();
    }
}

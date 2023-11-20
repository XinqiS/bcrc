package com.sooka.module.web.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.LbtService;
import com.sooka.mybatis.mapper.TCmsLbtMapper;
import com.sooka.mybatis.model.TCmsLbt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class LbtServiceImpl implements LbtService {

    @Autowired
    private TCmsLbtMapper lbtMapper;

    @Transactional(transactionManager = "masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public String save(TCmsLbt pojo) {
        pojo.setFlag(0);
        pojo.setPublisher("admin");
        pojo.setLbt_time(new Date());
        if(lbtMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","lbt-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
    }

    @Override
    public String update(TCmsLbt pojo) {
        if(lbtMapper.updateByPrimaryKeySelective(pojo)>0){
            return JsonUtil.toSUCCESS("操作成功!","lbt-tab",true);
        }
        return JsonUtil.toERROR("操作失败!");
    }

    @Override
    public String delete(Integer[] ids) {
        if(ids!=null&&ids.length>0){
            for(Integer id:ids){
                lbtMapper.deleteByPrimaryKey(id);
                return JsonUtil.toSUCCESS("删除成功!","lbt-tab",true);
            }
        }
        return JsonUtil.toERROR("删除失败!");
    }

    @Override
    public TCmsLbt findById(Integer id) {
        return lbtMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TCmsLbt> findList(TCmsLbt pojo) {
        return lbtMapper.select(pojo);
    }

    @Override
    public List<TCmsLbt> findAll() {
        return lbtMapper.selectAll();
    }

    @Override
    public PageInfo<TCmsLbt> page(Integer pageNumber, Integer pageSize, TCmsLbt pojo) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(findList(pojo));
    }

    @Override
    public PageInfo<TCmsLbt> page(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(findAll());
    }

}

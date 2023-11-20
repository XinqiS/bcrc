package com.sooka.module.web.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.RegisterService;
import com.sooka.mybatis.mapper.TCmsRegisterMapper;
import com.sooka.mybatis.model.TCmsRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TCmsRegisterMapper registerMapper;

    @Transactional(transactionManager = "masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public String save(TCmsRegister pojo) {
        pojo.setR_time(new Date());
        pojo.setR_state(1);
        if(registerMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!");
        }
        return JsonUtil.toERROR("保存失败!");
    }

    @Override
    public String update(TCmsRegister pojo) {
        return null;
    }

    @Override
    public String delete(Integer[] ids) {
        return null;
    }

    @Override
    public String delete(Integer id){
        if(registerMapper.del(id)>0){
            return JsonUtil.toSUCCESS("删除成功!","reg-tab",false);
        }
        return JsonUtil.toERROR("删除失败!");
    }

    @Override
    public TCmsRegister findById(Integer id) {
        return registerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TCmsRegister> findList(TCmsRegister pojo) {
        return registerMapper.select(pojo);
    }

    @Override
    public List<TCmsRegister> findAll() {
        return registerMapper.find();
    }

    @Override
    public PageInfo<TCmsRegister> page(Integer pageNumber, Integer pageSize, TCmsRegister pojo) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(findList(pojo));
    }

    @Override
    public PageInfo<TCmsRegister> page(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(findAll());
    }
}

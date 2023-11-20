package com.sooka.module.web.system.service;

import com.github.pagehelper.PageInfo;
import com.sooka.mybatis.model.TSysAttachment;

import java.util.List;

/**
 * Description:
 *
 **/
public interface AttachmentService{

    void save(TSysAttachment pojo);

    String delete(Long[] ids);

    TSysAttachment findByKey(String key);

    List<TSysAttachment> findList(TSysAttachment pojo);

    List<TSysAttachment> findAll();

    PageInfo<TSysAttachment> page(Integer pageNumber, Integer pageSize, TSysAttachment pojo);

    PageInfo<TSysAttachment> page(Integer pageNumber, Integer pageSize);

}

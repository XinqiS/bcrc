package com.sooka.module.web.cms.service.impl;

import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.EightCategoryZxbmService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.mapper.EightCategoryZxbmMapper;
import com.sooka.mybatis.model.EcZxbm;
import com.sooka.mybatis.model.Zxbm;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author limeng
 * @date 2021年12月17日 16:06
 */
@Service
public class EightCategoryZxbmServiceImpl implements EightCategoryZxbmService {
    @Autowired
    EightCategoryZxbmMapper eightCategoryZxbmMapper;

    @Override
    public String save(EcZxbm pojo) {
        pojo.setId(StrUtil.getUUID());
        if(eightCategoryZxbmMapper.insertSelective(pojo)>0){
            return JsonUtil.toSUCCESS("保存成功!","enterprise-tab",true);
        }
        return JsonUtil.toERROR("保存失败!");
    }

    @Override
    public List<EcZxbm> findByContentId(String contentId){
        return eightCategoryZxbmMapper.findByContentId(contentId);
    }

    @Override
    public List<EcZxbm> findByCategoryId(String categoryId,String zhId){
        return eightCategoryZxbmMapper.findByCategoryId(categoryId,zhId);
    }

    @Override
    public EcZxbm getOne(String id) {
        return eightCategoryZxbmMapper.getOne(id);
    }

    @Override
    public String update(EcZxbm pojo) {
        return null;
    }

    @Override
    public String delete(String[] ids) {
        return null;
    }

    @Override
    public EcZxbm findById(String id) {
        EcZxbm zxbm = new EcZxbm();
        zxbm.setId(id);
        return eightCategoryZxbmMapper.selectOne(zxbm);
    }

    @Override
    public List<EcZxbm> findList(EcZxbm pojo) {
        return null;
    }

    @Override
    public List<EcZxbm> findAll() {
        return null;
    }

    @Override
    public PageInfo<EcZxbm> page(Integer pageNumber, Integer pageSize, EcZxbm pojo) {
        return null;
    }

    @Override
    public PageInfo<EcZxbm> page(Integer pageNumber, Integer pageSize) {
        return null;
    }
}

package com.sooka.module.web.cms.service;

import com.sooka.common.base.BaseService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.EcZxbm;

import java.util.List;

/**
 * @author limeng
 * @date 2021年12月17日 16:05
 */
public interface EightCategoryZxbmService extends BaseService<EcZxbm,String> {

    List<EcZxbm> findByContentId(String contentId);

    List<EcZxbm> findByCategoryId(String categoryId,String zhId);

    EcZxbm getOne(String id);
}

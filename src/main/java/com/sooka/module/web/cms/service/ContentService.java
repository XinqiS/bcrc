package com.sooka.module.web.cms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.module.web.cms.vo.TCmsContentVo;
import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TCmsModelFiled;

/**
 * Description:内容
 *
 *
 * @create 2017-04-18
 **/
public interface  ContentService extends BaseService<TCmsContent,Long> {

    PageInfo<TCmsContent> page(Integer pageNumber, Integer pageSize, TCmsContentVo pojo);

    Map findContentByContentIdAndTableName(Long contentId, String tableName);
    /*回收站*/
    String recovery(Long[] ids);

    String release(Long[] ids);

    String save(TCmsContent content,String tableName, Map<String, Object> formParam,String[] tag) throws SQLException;

    String update(TCmsContent content, String tableName, List<TCmsModelFiled> cmsModelFileds , Map<String, Object> formParam, String[] tag) throws SQLException;

    PageInfo<Map> findContentListBySiteIdAndCategoryId(Integer siteId,
                                                               Long categoryId,
                                                               Integer orderBy,
                                                               Integer pageNumber,
                                                               Integer pageSize,
                                                               Integer hasChild,
                                                               Integer isHot,
                                                               Integer isPic,
                                                               Integer isRecommend);

    PageInfo<Map> findTopicContentListBySiteIdAndCategoryIds(Integer siteId,
                                                        Long[] categoryIds,
                                                        Integer orderBy,
                                                        Integer size,
                                                        Integer isHot,
                                                        Integer isPic,
                                                        Integer isRecommend);

    PageInfo<Map> page(Integer pageNumber,Integer siteId,Long categoryId);

    PageInfo<TCmsContent>  findContentListByModelFiledValue(int pageNumber,Long categoryId, String tableName, Map<String, Object> param);

    void viewUpdate(Long contentId);

    String findAllMonthCount();

    Integer AllCount();

    List<TCmsContent> findByCategoryId(Long categoryId);
    
    List<Map<String,Object>> FuzzyQueryTitle(String title);

	TCmsContent selectOne(String contentId);

    PageInfo<TCmsContent> pageSousuo(Integer pageNumber, Integer pageSize, TCmsContentVo pojo);

}

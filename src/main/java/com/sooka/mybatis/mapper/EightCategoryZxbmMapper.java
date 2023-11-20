package com.sooka.mybatis.mapper;

import com.sooka.module.web.system.vo.gjss;
import com.sooka.mybatis.model.EcZxbm;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author limeng
 * @date 2021年12月17日 16:07
 */
public interface EightCategoryZxbmMapper extends Mapper<EcZxbm> {
    @Select("select * from tb_ec_zxbm where content_id=#{contentId}")
    @Results(id = "ecZxbmMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "wentToSchool", column = "went_to_school"),
            @Result(property = "idNumber", column = "id_number"),
            @Result(property = "graduationTime", column = "graduation_time"),
            @Result(property = "contentId", column = "content_id")
    })
    List<EcZxbm> findByContentId(@Param("contentId") String contentId);

    @Select("SELECT * FROM tb_ec_zxbm a LEFT JOIN t_cms_content b ON a.content_id = b.content_id WHERE b.category_id = #{categoryId} AND zh_id =#{zhId}")
    @Results(id = "ecGrzxMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "wentToSchool", column = "went_to_school"),
            @Result(property = "idNumber", column = "id_number"),
            @Result(property = "graduationTime", column = "graduation_time"),
            @Result(property = "contentId", column = "content_id")
    })
    List<EcZxbm> findByCategoryId(@Param("categoryId") String categoryId, @Param("zhId") String zhId);

    @Select("SELECT id,name,sex,nation,birthday,tel,id_number,education,graduation_time,went_to_school,content_id,zh_id FROM tb_ec_zxbm WHERE id =#{id}")
    @ResultMap(value = "ecGrzxMap")
    EcZxbm getOne(@Param("id") String id);


}

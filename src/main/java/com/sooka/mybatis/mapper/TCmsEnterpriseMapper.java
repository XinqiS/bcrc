package com.sooka.mybatis.mapper;



import java.util.List;
import java.util.Map;

import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.TCmsEnterprise;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TCmsEnterpriseMapper extends Mapper<tb_qy_info> {

	List<tb_qy_info> selectFamousEnterprise(Map<String,Object> map);


}

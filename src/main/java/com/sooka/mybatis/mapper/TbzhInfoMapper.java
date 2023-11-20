package com.sooka.mybatis.mapper;



import java.util.List;

import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.TSysUser;
import org.apache.ibatis.annotations.Select;

import com.sooka.module.web.system.vo.tb_zh_info;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface TbzhInfoMapper extends Mapper<tb_zh_info> {

	
	@Select("select count(info.zh_id) from tb_zh_info info where info.zh_user=#{0}")
	int QueryWhetherTheMailBoxIsUnique(String zh_user);
	
	@Select("select * from tb_zh_info  where zh_user=#{ arg0} and zh_pwd=#{ arg1}")
	tb_zh_info  SignInForIndividualsAndBusinesses(String zh_user,String zh_pwd);

	@Select("select * from tb_zh_info  where  zh_pwd=#{arg0} and zh_user=#{arg1}")
	tb_zh_info  selectJmm(String zh_pwd,String zh_user);

	@Select("select * from tb_zh_info  where  zh_id=#{zh_id}")
	tb_zh_info  selectByZhId(String zh_id);

	@Select("select z.* from tb_zh_info z where  z.zh_sf = 1 and z.ZH_ID not in(select a.ZH_ID from tb_qy_info a)")
	List<tb_zh_info>  selectZh();

	@Update("update tb_zh_info set\n" +
			"\t   zh_user = #{zh_user},\n" +
			"\t   zh_pwd = #{zh_pwd},\n" +
			"\t   zh_sf = #{zh_sf}\n" +
			"\t   where zh_id = #{zh_id}")
	public boolean updateByZhId(tb_zh_info pojo);


	@Select("<script>"
			+ "select * from tb_zh_info "
			+ "where 1=1"
			+ "<if test='zh_user != null and zh_user  !=\"\"'>and (zh_user like CONCAT('%',#{zh_user},'%')) </if>"
			+ "<if test='zh_sf !=null '>and zh_sf= #{zh_sf}</if>"
			+ "</script>")
	List<tb_zh_info> selectZhAll(tb_zh_info info);

}
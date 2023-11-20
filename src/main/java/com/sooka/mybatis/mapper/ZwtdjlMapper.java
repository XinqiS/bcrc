package com.sooka.mybatis.mapper;

import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.JlAll;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.sooka.module.web.system.vo.tb_zwtdjl;

import java.util.List;
import java.util.Map;

public interface ZwtdjlMapper extends Mapper<tb_zwtdjl>{
	


	@Select("delete  from TB_ZWTDJL where TDJL_ID=#{0}")
	public void deleteByPrimiryKeyForTbZwtdjl(String id);

	@Select("SELECT	count( * ) FROM	TB_ZWTDJL tdjl WHERE	tdjl.ZW_ID IN ( SELECT gw.ZW_ID FROM tb_gw_info gw WHERE gw.QY_ID IN ( SELECT QY_ID FROM tb_qy_info qy WHERE qy.ZH_ID = #{0} ) );")
	public int selectCountByZh_id(String id);

	@Select("SELECT	count( * ) FROM	TB_ZWTDJL tdjl WHERE	tdjl.ZW_ID IN ( SELECT gw.ZW_ID FROM tb_gw_info gw WHERE gw.QY_ID IN ( SELECT QY_ID FROM tb_qy_info qy WHERE qy.ZH_ID = #{0} ) ) 	AND tdjl.TDJL_TDZT = 1;")
	public int selectCountTdztByZh_id(String id);

	@Update("UPDATE tb_zwtdjl SET tdjl_tdzt =#{tdjl_tdzt},tdjl_mszt = #{tdjl_mszt},tdjl_yqsj = #{tdjl_yqsj} WHERE tdjl_id = #{tdjl_id}")
	public void updateMsyqByPrimiryKeyForAll(tb_zwtdjl tdjl);


/*	public List<JlAll> searchTalents(@Param("cshy") String cshy, @Param("nl") String nl, @Param("nl1") String nl1, @Param("xb") String xb,
									  @Param("gznx") String gznx, @Param("gznx1") String gznx1, @Param("xl") String xl, @Param("xl1") String xl1);*/

	public List<JlAll> searchTalents(@Param("cshy") String cshy, @Param("nl") String nl, @Param("nl1") String nl1, @Param("xb") String xb,
									 @Param("gznx") String gznx, @Param("gznx1") String gznx1, @Param("xl") String xl, @Param("xl1") String xl1);


}

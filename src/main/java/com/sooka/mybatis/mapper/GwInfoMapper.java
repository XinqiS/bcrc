package com.sooka.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_gw_info;

public interface GwInfoMapper extends Mapper<tb_gw_info>{
	@Delete("DELETE FROM tb_gw_info WHERE ZW_ID = #{0}")
	void deleteById(String zw_id);
	
	@Select("select * from tb_gw_info where zw_id=#{0}")
	tb_gw_info selectZw_id(String zw_id);
	@Select("select * from tb_gw_info  where qy_id=#{0} and zw_zt=0 order by  zw_fbsc desc")
	List<tb_gw_info> selectByQyId(String qyid);

	@Select("select * from tb_gw_info  where qy_id=#{0} and zw_zt=0 order by  zw_fbsc desc")
	List<Map<String,Object>>  selectByQyIdquery(String qyid);
	
	@Select("<script>"
 + "select gw.zw_id,qy.qy_id,gw.zw_zwmc,qy.qy_qymc,gw.zw_yxfw,qy.qy_xxdz,qy.qy_jjlx,gw.zw_zdxl,gw.zw_gznx,gw.zw_gzdz,gw.zw_zprs,gw.zw_zwld,gw.zw_fbsj,gw.zw_fbsc,gw.zw_zplx from TB_GW_INFO gw,TB_QY_INFO qy "
			+ "where 1=1 and gw.QY_ID=qy.QY_ID"
			+ "<if test='mc != null and mc  !=\"\"'>and (gw.zw_zwmc like CONCAT('%',#{mc},'%') or qy.qy_qymc like CONCAT('%',#{mc},'%')) </if>"
			+ "<if test='dd != null and dd  !=\"\"'>and (gw.zw_gzdz like CONCAT('%',#{dd},'%') or qy.qy_xxdz like CONCAT('%',#{dd},'%')) </if>"
			+ "<if test='zwlb !=null and zwlb.size() != 0  '>and gw.zw_zwlb in <foreach item='item' index='index' collection='zwlb' open='(' separator=',' close=')'> #{item} </foreach> </if>"
			+ "<if test='sshy !=null and sshy.size() != 0 '>and qy.qy_sshy in <foreach item='item' index='index' collection='sshy' open='(' separator=',' close=')'> #{item} </foreach> </if>"
			+ "<if test='qy_jjlx !=null and qy_jjlx != -1'>and qy.qy_jjlx= #{qy_jjlx}</if>"
			+ "<if test='zw_gznx !=null and zw_gznx != -1'>and gw.zw_gznx= #{zw_gznx}</if>"
			+ "<if test='zw_yxfw !=null and zw_yxfw != -1'>and gw.zw_yxfw= #{zw_yxfw}</if>"
			+ "<if test='zw_zdxl !=null and zw_zdxl != -1'>and gw.zw_zdxl= #{zw_zdxl}</if>"
			+ "<if test='zw_gzxz !=null and zw_gzxz != -1'>and gw.zw_gzxz= #{zw_gzxz}</if>"
			+ "<if test='gsgm != null and gsgm != -1'>and qy.qy_qygm= #{gsgm}</if>"
			+ "<if test='xzfl != null and xzfl != -1'>and gw.zw_zwld LIKE CONCAT('%',#{xzfl},'%') </if>"
			+ "<if test='fbrq!=null and fbrq != -1'>and (gw.zw_fbsj between #{startDate}  and  #{endDate})</if>"
			+ "<if test='zw_yjbys!=null and zw_yjbys  !=\"\"'>and zw_yjbys=#{zw_yjbys}</if>"
			+ "<if test='zw_zt!=null'>and zw_zt=#{zw_zt}</if>"
			+ "<if test='zw_zplx != null and zw_zplx != -1'>and gw.zw_zplx = #{zw_zplx} </if>"
			+ "order by gw.zw_fbsc desc"
			+ "</script>")
	List<Map<String,Object>> selectExampleGjss(gjss g);

	@Select("select * from tb_qy_info qy, tb_gw_info gw where gw.ZW_SFTJ=1 and gw.QY_ID=qy.QY_ID")
	List<gjss> gjrcZgjgw();



	@Select("select * from tb_qy_info qy, tb_gw_info gw where  gw.QY_ID=qy.QY_ID")
	List<gjss> gjrcZgjgwAll();
}

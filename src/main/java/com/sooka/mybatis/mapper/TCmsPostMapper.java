package com.sooka.mybatis.mapper;




import java.util.List;

import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.module.web.system.vo.tb_zwtdjl;
import com.sooka.mybatis.model.TCmsPost;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface TCmsPostMapper extends Mapper<tb_gw_info> {
	
	List<tb_gw_info> selectPostByEnterpriseId(tb_gw_info post);

	@Select("<script>"
			+"select * from tb_gw_info i left join tb_qy_info q on i.qy_id = q.qy_id"
			+" where i.qy_id=#{qy_id}"
			+"<if test='zw_zwmc != null and zw_zwmc  !=\"\"'>and (i.zw_zwmc like CONCAT('%',#{zw_zwmc},'%')) </if>"
			+ "<if test='zw_sftj !=null'>and i.zw_sftj=#{zw_sftj}</if>"
			+ "<if test='zw_zt !=null'>and i.zw_zt= #{zw_zt}</if>"
			+ " order by i.zw_fbsc desc"
			+ "</script>")
	List<tb_gw_info> selectPostByGwId(tb_gw_info post);


	@Delete("delete from tb_gw_info where zw_id = #{zw_id}")
	int deleteByZwId(@Param("zw_id") String zw_id);

	@Update(" update tb_gw_info set\n" +
			"\t  qy_id = #{qy_id},\n" +
			"   zw_sftj = #{zw_sftj},\n" +
			"   zw_zwmc = #{zw_zwmc},\n" +
			"   zw_zwlb = #{zw_zwlb},\n" +
			"   zw_gzxz = #{zw_gzxz},\n" +
			"   zw_gzdz = #{zw_gzdz},\n" +
			"   zw_yxfw = #{zw_yxfw},\n" +
			"   zw_zdxl = #{zw_zdxl},\n" +
			"   zw_gznx = #{zw_gznx},\n" +
			"   zw_zwms = #{zw_zwms},\n" +
			"   zw_jnyq = #{zw_jnyq},\n" +
			"   zw_zwld = #{zw_zwld},\n" +
			"   zw_zprs = #{zw_zprs},\n" +
			"   zw_fbsc = #{zw_fbsc},\n" +
			"   zw_ssbm = #{zw_ssbm}, \n" +
			"   zw_zt   = #{zw_zt}, \n" +
			"   zw_fbsj = #{zw_fbsj}, \n" +
			"   zw_gxrq = #{zw_gxrq}, \n" +
			"   zw_yjbys = #{zw_yjbys} where zw_id = #{zw_id}")
	public boolean updateByZwId(tb_gw_info pojo);

	@Update(" update tb_gw_info set  zw_sftj = #{zw_sftj} where zw_id = #{zw_id}")
	public boolean updateZwSftj(tb_gw_info pojo);

}

package com.sooka.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.sooka.module.web.system.vo.tb_qy_info;


public interface QyInfoMapper extends Mapper<tb_qy_info>{
	@Select("select * from tb_qy_info where zh_id=#{0}")
	tb_qy_info selectQyQymcByZhId(String id);

    @Select("select \n" +
            "   QY_ID,\n" +
            "   ZH_ID,\n" +
            "\n" +
            "(CASE QY_SFTJ WHEN 0 THEN '否'  WHEN 1 THEN '知名企业' WHEN 2 THEN '名企招聘' ELSE 'null' END) QY_SFTJ,\n" +
            "\n" +
            "   QY_QYMC,\n" +
            "   QY_SSHY,\n" +
            "(CASE QY_JJLX WHEN 0 THEN '国有经济'  WHEN 1 THEN '集体经济' WHEN 2 THEN '私营经济' \n" +
            " WHEN 3 THEN '个体经济' \n" +
            " WHEN 4 THEN '联营经济' \n" +
            " WHEN 5 THEN '股份制' \n" +
            " WHEN 6 THEN '外商投资' \n" +
            " WHEN 7 THEN '港澳台投资' \n" +
            " WHEN 8 THEN '其他经济类' \n" +
            "ELSE 'null' END) QY_JJLX,\n" +
            "(CASE QY_QYGM WHEN 0 THEN '20以下'  WHEN 1 THEN '20-99' WHEN 2 THEN '100-499' \n" +
            " WHEN 3 THEN '500-999' \n" +
            " WHEN 4 THEN '1000-9999' \n" +
            " WHEN 5 THEN '10000以上'  \n" +
            "ELSE 'null' END) QY_QYGM ,\n" +
            "   QY_QYJL,\n" +
            "   QY_QYFL,\n" +
            "   QY_QYWZ,\n" +
            "   QY_LXR,\n" +
            "   QY_LXDH,\n" +
            "   QY_YX,\n" +
            "   QY_XXDZ,\n" +
            "   QY_FILE_ZZ,\n" +
            "   QY_FILE_LOGO\n" +
            "from tb_qy_info where ZH_ID=#{zhId}")
    public tb_qy_info selectByzhInfoId(String zhId);


    @Select("select * from tb_qy_info where ZH_ID=#{zhId}")
    public tb_qy_info selectByzhId(String zhId);
    
	@Select("select * from tb_qy_info where qy_sftj=#{arg0} limit #{arg1}")
	public List<tb_qy_info> selectMq(Integer qy_sftj, Integer size);

    @Select("<script>"
            + "select * from tb_qy_info "
            + "where 1=1"
            + "<if test='qy_qymc != null and qy_qymc  !=\"\"'>and (qy_qymc like CONCAT('%',#{qy_qymc},'%')) </if>"
            + "<if test='qy_spzt !=null'>and qy_spzt=#{qy_spzt}</if>"
            + "<if test='qy_sftj !=null'>and qy_sftj= #{qy_sftj}</if>"
            +"order by qy_fbsc desc"
            + "</script>")
    List<tb_qy_info> selectQy(tb_qy_info qy);
    
    @Delete("delete from tb_zwtdjl where ZW_ID in (select ZW_ID from tb_gw_info where QY_ID=#{QY_ID})")
	void deleteTdjlByQyId(@Param("QY_ID") String qyId);
    
    @Delete("delete from tb_zwscjl where ZW_ID in (select ZW_ID from tb_gw_info where QY_ID=#{QY_ID})")
	void deleteByZwscByQyId(@Param("QY_ID") String qyId);
    
    @Delete("delete from tb_jllljl where QY_ID=#{QY_ID}")
	void deleteByJllljlByQyId(@Param("QY_ID") String qyId);
    
    @Delete("delete from tb_gw_info where QY_ID=#{QY_ID}")
	void deleteGwByQyId(@Param("QY_ID") String qyId);
}

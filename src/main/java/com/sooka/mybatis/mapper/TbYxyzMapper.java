package com.sooka.mybatis.mapper;





import org.apache.ibatis.annotations.Select;




import com.sooka.module.web.system.vo.tb_yxyz;

import tk.mybatis.mapper.common.Mapper;

public interface TbYxyzMapper extends Mapper<tb_yxyz> {
	@Select("SELECT yz.YZ_ID,yz.YZ_YZM,yz.YZ_YZSJ,yz.`YZ_YX` FROM tb_yxyz AS yz WHERE yz.`YZ_YX` = #{0} ORDER BY yz.YZ_YZSJ desc LIMIT 0, 1")
	tb_yxyz selectByYHM(String zh_user);

}
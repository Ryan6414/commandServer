package org.mudules.cmd.nbrlsb.nbFouroffenderInfo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.entity.NbFouroffenderInfo;

public interface NbFouroffenderInfoMapper extends BaseMapper<NbFouroffenderInfo> {


    @Select("select * from nb_fouroffender_info  where upload_flag in (0,2) limit 1")
    NbFouroffenderInfo getOneNbFouroffenderInfo();

    @Update("update nb_fouroffender_info set upload_flag = #{upFlag} where id= #{id} ")
    int updateUploadFlagById(@Param("upFlag") String upFlag, @Param("id") String id);

    @Update("update nb_fouroffender_info set upload_flag = #{upFlag} where upload_flag= #{id} ")
    int updateUploadFlagByFalg(@Param("upFlag") String upFlag, @Param("id") String id);
}

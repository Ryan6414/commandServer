package org.mudules.cmd.nbrlsb.NbPoliceInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.NbPoliceInfo.entity.NbPoliceInfo;


public interface NbPoliceInfoMapper extends BaseMapper<NbPoliceInfo> {

    @Select("select * from nb_police_info where upload_flag in (0,2) limit 1")
    NbPoliceInfo getOneNbPoliceInfo();

    @Update("update nb_police_info  set upload_flag = #{upFlag} where id= #{id}")
    int updateUploadFlagById(@Param("upFlag") String upFlag, @Param("id") String id);


    @Update("update nb_police_info set upload_flag = #{upFlag} where upload_flag= #{id} ")
    int updateUploadFlagByFalg(@Param("upFlag") String upFlag, @Param("id") String id);


}

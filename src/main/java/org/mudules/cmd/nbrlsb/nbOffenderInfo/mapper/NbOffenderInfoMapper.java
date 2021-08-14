package org.mudules.cmd.nbrlsb.nbOffenderInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;
import org.mudules.cmd.nbrlsb.nbOffenderInfo.entity.NbOffenderInfo;


public interface NbOffenderInfoMapper extends BaseMapper<NbOffenderInfo> {
    @Select("select * from nb_offender_info where upload_flag in(0,2) limit 1")
    NbOffenderInfo getOneNbOffenderInfo();

    @Update("update nb_offender_info set upload_flag = #{upFlag} where id= #{id}")
    int updateUploadFlagById(@Param("upFlag") String upFlag,@Param("id") String id);


    @Update("update nb_offender_info set upload_flag = #{upFlag} where upload_flag= #{id} ")
    int updateUploadFlagByFalg(@Param("upFlag") String upFlag, @Param("id") String id);

}

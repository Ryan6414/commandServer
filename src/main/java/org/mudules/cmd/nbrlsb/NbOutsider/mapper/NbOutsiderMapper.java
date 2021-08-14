package org.mudules.cmd.nbrlsb.NbOutsider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;



public interface NbOutsiderMapper extends BaseMapper<NbOutsider> {

    @Select("select * from nb_outsider where upload_flag in(0,2) limit 1")
    NbOutsider getOneNboutsider();

    @Update("update nb_outsider set upload_flag = #{upFlag} where id= #{id}")
    int updateUploadFlagById(@Param("upFlag") String upFlag, @Param("id") String id);

    @Update("update nb_outsider set upload_flag = #{upFlag} where upload_flag= #{id} ")
    int updateUploadFlagByFalg(@Param("upFlag") String upFlag, @Param("id") String id);

}

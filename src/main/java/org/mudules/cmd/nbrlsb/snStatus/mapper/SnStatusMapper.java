package org.mudules.cmd.nbrlsb.snStatus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;

public interface SnStatusMapper extends BaseMapper<SnStatus> {

    @Select("Select status from nb_sending_status where sn = #{sn}")
    Integer getSnStatus(String sn);

    @Update("update nb_sending_status set status = #{status} where sn = #{sn}")
    Boolean updateSnStatus(Integer status, String sn);
}

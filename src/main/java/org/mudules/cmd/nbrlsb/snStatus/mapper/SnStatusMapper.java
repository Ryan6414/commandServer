package org.mudules.cmd.nbrlsb.snStatus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;

import java.util.List;
import java.util.Map;

public interface SnStatusMapper extends BaseMapper<SnStatus> {

    // 避免重复请求的sql
    @Select("select count(*) from nb_sending_status where area= #{area} and status in (1,9)")
    int getStaByArea(@Param("area") Integer area);

    // 查询外来人员设备上传状态
    @Select("Select status,area from nb_sending_status where sn = #{sn}")
    Map<String, Integer> getSnStatus(@Param("sn") String sn);

    // 全部变成9
    @Update("update nb_sending_status set status = 9")
    int updateAllStatus();

    // 根据id变成9
    @Update("update nb_sending_status set status = 9 where id = #{id}")
    int updateAllStatusById(@Param("id") Integer id);

    // 将第一个9改成1
    @Update("update nb_sending_status set status = 1 where area = #{area}  ORDER BY id  limit 1")
    int updateOneStatusByArea(@Param("area") Integer area);

    // 根据area查询对应status状态
    @Select("select * from nb_sending_status where area = #{area}")
    List<SnStatus> getObjectByArea(@Param("area") Integer area);


    @Update("update nb_sending_status set status = #{status} where sn = #{sn}")
    Boolean updateSnStatus(@Param("status") Integer status, @Param("sn") String sn);


    @Update("update nb_sending_status set status = 1 where id = 1")
    int updateOneStatus();


    @Update("update nb_sending_status set status = #{s1} where status = #{s2} limit 1")
    int updateStatusByStatus(@Param("s1") Integer s1, @Param("s2") Integer s2);

    @Select("select count(*) from  nb_sending_status where status = 9 ")
    int getStatus_9();

    // 查看Status状态
//    @Select("select * from nb_sending_status ")
//    List<SnStatus> getStatusInfo();
}

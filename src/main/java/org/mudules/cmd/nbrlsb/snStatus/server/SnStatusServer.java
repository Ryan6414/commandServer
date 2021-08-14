package org.mudules.cmd.nbrlsb.snStatus.server;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;

import java.util.List;
import java.util.Map;

public interface SnStatusServer  extends IService<SnStatus> {
    Boolean updateSnStatus(Integer status, String sn);

    Map<String,Integer> getSnStatus(String sn);

    int updateAllStatusById( Integer id);

    int updateAllStatus();

    int updateOneStatus();

    int getStatus_9();

    int updateStatusByStatus(Integer s1, Integer s2 );


    int updateOneStatusByArea(Integer area);

    List<SnStatus> getObjectByArea( Integer area);

    /**
     * 查看设备注册信息
     * @return list
     */
    List<SnStatus> getStatusInfo();

    boolean getStaByArea(Integer area);
}

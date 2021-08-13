package org.mudules.cmd.nbrlsb.snStatus.server;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;

public interface SnStatusServer  extends IService<SnStatus> {
    Boolean updateSnStatus(Integer status, String sn);
    Integer getSnStatus(String sn);
}

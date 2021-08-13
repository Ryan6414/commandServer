package org.mudules.cmd.nbrlsb.snStatus.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;
import org.mudules.cmd.nbrlsb.snStatus.mapper.SnStatusMapper;
import org.mudules.cmd.nbrlsb.snStatus.server.SnStatusServer;
import org.springframework.stereotype.Service;

@Service
public class SnStatusServerImpl extends ServiceImpl<SnStatusMapper, SnStatus> implements SnStatusServer {


    @Override
    public Boolean updateSnStatus(Integer status, String sn) {
        return super.baseMapper.updateSnStatus(status,sn);
    }

    @Override
    public Integer getSnStatus(String sn) {
        return super.baseMapper.getSnStatus(sn);
    }
}

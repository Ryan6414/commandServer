package org.mudules.cmd.nbrlsb.snStatus.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;
import org.mudules.cmd.nbrlsb.snStatus.mapper.SnStatusMapper;
import org.mudules.cmd.nbrlsb.snStatus.server.SnStatusServer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SnStatusServerImpl extends ServiceImpl<SnStatusMapper, SnStatus> implements SnStatusServer {


    @Override
    public Boolean updateSnStatus(Integer status, String sn) {
        return super.baseMapper.updateSnStatus(status,sn);
    }

    @Override
    public Map<String, Integer> getSnStatus(String sn) {
        return super.baseMapper.getSnStatus(sn);
    }

    @Override
    public int updateAllStatusById(Integer id) {
        return super.baseMapper.updateAllStatusById(id);
    }

    @Override
    public int updateAllStatus() {
        return super.baseMapper.updateAllStatus();
    }

    @Override
    public int updateOneStatus() {
        return super.baseMapper.updateOneStatus();
    }

    @Override
    public int getStatus_9() {
        return super.baseMapper.getStatus_9();
    }

    @Override
    public int updateStatusByStatus(Integer s1, Integer s2) {
        return super.baseMapper.updateStatusByStatus(s1,s2);
    }

    @Override
    public int updateOneStatusByArea(Integer area) {
        return super.baseMapper.updateOneStatusByArea(area);
    }

    @Override
    public List<SnStatus> getObjectByArea(Integer area) {
        return super.baseMapper.getObjectByArea(area);
    }

    /**
     * 查看设备注册信息
     *
     * @return list
     */
    @Override
    public List<SnStatus> getStatusInfo() {
        return super.baseMapper.selectList(null);
    }

    @Override
    public boolean getStaByArea(Integer area) {

        if(super.baseMapper.getStaByArea(area) > 0){
            return false;
        }
        return true;
    }
}

package org.mudules.cmd.nbrlsb.NbPoliceInfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.mudules.cmd.nbrlsb.NbPoliceInfo.entity.NbPoliceInfo;
import org.mudules.cmd.nbrlsb.NbPoliceInfo.mapper.NbPoliceInfoMapper;
import org.mudules.cmd.nbrlsb.NbPoliceInfo.service.INbPoliceInfoService;
import org.springframework.stereotype.Service;


@Service
public class NbPoliceInfoServiceImpl extends ServiceImpl<NbPoliceInfoMapper, NbPoliceInfo> implements INbPoliceInfoService {


    @Override
    public NbPoliceInfo getOnePolice() {
        return super.baseMapper.getOneNbPoliceInfo();
    }

    @Override
    public int updateUploadFlagById(String upFlag, String id) {
        return super.baseMapper.updateUploadFlagById(upFlag,id);
    }

    @Override
    public int updateUploadFlagByFalg(String upFlag, String  id) {
        return super.baseMapper.updateUploadFlagByFalg(upFlag,id);
    }


}

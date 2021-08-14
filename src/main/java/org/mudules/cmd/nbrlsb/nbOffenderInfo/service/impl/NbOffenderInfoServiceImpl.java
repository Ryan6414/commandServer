package org.mudules.cmd.nbrlsb.nbOffenderInfo.service.impl;


import org.mudules.cmd.nbrlsb.nbOffenderInfo.entity.NbOffenderInfo;
import org.mudules.cmd.nbrlsb.nbOffenderInfo.mapper.NbOffenderInfoMapper;
import org.mudules.cmd.nbrlsb.nbOffenderInfo.service.INbOffenderInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: nb_offender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Service
public class NbOffenderInfoServiceImpl extends ServiceImpl<NbOffenderInfoMapper, NbOffenderInfo> implements INbOffenderInfoService {

    @Override
    public NbOffenderInfo getOneNbOffenderInfo() {
        return super.baseMapper.getOneNbOffenderInfo();
    }

    @Override
    public int updateUploadFlagById(String upFlag, String id) {
        return super.baseMapper.updateUploadFlagById(upFlag,id);
    }

    @Override
    public int updateUploadFlagByFalg(String upFlag, String id) {
        return super.baseMapper.updateUploadFlagByFalg(upFlag,id);
    }
}

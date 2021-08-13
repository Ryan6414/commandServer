package org.jeecg.modules.netboat.nbOffenderInfo.service.impl;

import org.jeecg.modules.netboat.fourprisonManagement.entity.NbFourprisonManagement;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;
import org.jeecg.modules.netboat.nbOffenderInfo.mapper.NbOffenderInfoMapper;
import org.jeecg.modules.netboat.nbOffenderInfo.service.INbOffenderInfoService;
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
    public NbOffenderInfo getByIdCardNo(String  idCardNumber) {
        return this.baseMapper.getByIdCardNo(idCardNumber);
    }
    @Override
    public String getName(String  number) {
        return this.baseMapper.getName(number);
    }
}

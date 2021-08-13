package org.jeecg.modules.netboat.nbFouroffenderInfo.service.impl;

import org.jeecg.modules.netboat.nbFouroffenderInfo.entity.NbFouroffenderInfo;
import org.jeecg.modules.netboat.nbFouroffenderInfo.mapper.NbFouroffenderInfoMapper;
import org.jeecg.modules.netboat.nbFouroffenderInfo.service.INbFouroffenderInfoService;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: nb_fouroffender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Service
public class NbFouroffenderInfoServiceImpl extends ServiceImpl<NbFouroffenderInfoMapper, NbFouroffenderInfo> implements INbFouroffenderInfoService {
    @Override
    public NbFouroffenderInfo getByIdCardNo(String  idCardNumber) {
        return this.baseMapper.getByIdCardNo(idCardNumber);
    }
    @Override
    public String getName(String  number) {
        return this.baseMapper.getName(number);
    }
}

package org.mudules.cmd.nbrlsb.nbFouroffenderInfo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.entity.NbFouroffenderInfo;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.mapper.NbFouroffenderInfoMapper;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.service.INbFouroffenderInfoService;
import org.springframework.stereotype.Service;


/**
 * @Description: nb_fouroffender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Service
public class NbFouroffenderInfoServiceImpl extends ServiceImpl<NbFouroffenderInfoMapper, NbFouroffenderInfo> implements INbFouroffenderInfoService {

    @Override
    public NbFouroffenderInfo getOneNbFouroffenderInfo() {
        return super.baseMapper.getOneNbFouroffenderInfo();
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

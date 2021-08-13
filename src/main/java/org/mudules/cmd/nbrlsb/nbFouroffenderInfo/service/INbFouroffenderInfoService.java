package org.jeecg.modules.netboat.nbFouroffenderInfo.service;

import org.jeecg.modules.netboat.nbFouroffenderInfo.entity.NbFouroffenderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;

/**
 * @Description: nb_fouroffender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
public interface INbFouroffenderInfoService extends IService<NbFouroffenderInfo> {
    NbFouroffenderInfo getByIdCardNo(String  idCardNumber);
    String getName(String  number);
}

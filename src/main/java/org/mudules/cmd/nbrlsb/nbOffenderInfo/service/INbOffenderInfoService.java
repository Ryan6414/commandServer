package org.jeecg.modules.netboat.nbOffenderInfo.service;

import org.jeecg.modules.netboat.fourprisonManagement.entity.NbFourprisonManagement;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: nb_offender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
public interface INbOffenderInfoService extends IService<NbOffenderInfo> {
    NbOffenderInfo getByIdCardNo(String  idCardNumber);
    String getName(String  number);
}

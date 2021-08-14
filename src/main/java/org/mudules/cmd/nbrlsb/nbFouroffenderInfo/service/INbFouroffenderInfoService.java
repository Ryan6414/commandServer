package org.mudules.cmd.nbrlsb.nbFouroffenderInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.entity.NbFouroffenderInfo;

public interface INbFouroffenderInfoService extends IService<NbFouroffenderInfo> {
    NbFouroffenderInfo getOneNbFouroffenderInfo();


    int updateUploadFlagById(String upFlag, String id);

    int updateUploadFlagByFalg(String upFlag, String id);
}

package org.mudules.cmd.nbrlsb.nbOffenderInfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.nbOffenderInfo.entity.NbOffenderInfo;


public interface INbOffenderInfoService extends IService<NbOffenderInfo> {
    NbOffenderInfo getOneNbOffenderInfo();

    int updateUploadFlagById(String upFlag, String id);


    int updateUploadFlagByFalg(String upFlag, String id);
}

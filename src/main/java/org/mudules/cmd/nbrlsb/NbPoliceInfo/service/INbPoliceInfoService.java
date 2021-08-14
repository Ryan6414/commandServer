package org.mudules.cmd.nbrlsb.NbPoliceInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.NbPoliceInfo.entity.NbPoliceInfo;


public interface INbPoliceInfoService extends IService<NbPoliceInfo> {

    NbPoliceInfo getOnePolice();

    int updateUploadFlagById(String upFlag, String id);


    int updateUploadFlagByFalg(String upFlag, String id);


}

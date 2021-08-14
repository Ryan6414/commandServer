package org.mudules.cmd.nbrlsb.NbOutsider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Update;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;

import java.util.List;

/**
 * @Description: nb_outsider
 * @Author: jeecg-boot
 * @Date:   2021-07-15
 * @Version: V1.0
 */
public interface INbOutsiderService extends IService<NbOutsider> {

//   List<NbOutsider> getAll();

   // 获取一个人员信息
   NbOutsider getOneNboutsider();



   int updateUploadFlagById(String upFlag, String id);


   int updateUploadFlagByFalg(String upFlag, String id);

//   NbOutsider getOneInfo(Integer up , Integer del);
//
//   Boolean updateByUpLoad(Integer upload, String id);
}

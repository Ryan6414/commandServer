package org.jeecg.modules.netboat.nbOffenderInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.netboat.fourprisonManagement.entity.NbFourprisonManagement;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: nb_offender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
public interface NbOffenderInfoMapper extends BaseMapper<NbOffenderInfo> {
    @Select("select * from nb_offender_info where id_card_number = #{idCardNumber} and del_flag = 0")
    NbOffenderInfo getByIdCardNo(@Param("idCardNumber") String idCardNumber);
    @Select("select name from nb_offender_info where id_card_number = #{number} and del_flag = '0'")
    String getName(@Param("number") String number);
}

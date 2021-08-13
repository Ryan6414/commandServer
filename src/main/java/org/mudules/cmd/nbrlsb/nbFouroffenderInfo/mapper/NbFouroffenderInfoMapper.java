package org.jeecg.modules.netboat.nbFouroffenderInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.netboat.nbFouroffenderInfo.entity.NbFouroffenderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;

/**
 * @Description: nb_fouroffender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
public interface NbFouroffenderInfoMapper extends BaseMapper<NbFouroffenderInfo> {
    @Select("select * from nb_fouroffender_info where id_card_number = #{idCardNumber} and del_flag = 0")
    NbFouroffenderInfo getByIdCardNo(@Param("idCardNumber") String idCardNumber);
    @Select("select name from nb_fouroffender_info where id_card_number = #{number} and del_flag = '0'")
    String getName(@Param("number") String number);
}

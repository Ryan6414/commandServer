package org.mudules.cmd.nbrlsb.snStatus.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("nb_sending_status")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SnStatus {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 设备号
     */
    private String sn;
    /**
     * 上传状态
     */
    private Integer status;


    /**
     * 设备在什么区域
     */
    private Integer area;

}

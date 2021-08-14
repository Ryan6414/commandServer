package org.mudules.cmd.nbrlsb.NbPoliceInfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("nb_police_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="nb_police_info对象", description="nb_police_info")
public class NbPoliceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**警号*/


    @ApiModelProperty(value = "警号")
    private String policeNumber;
	/**姓名*/

    @ApiModelProperty(value = "姓名")
    private String name;
	/**性别*/

	private String uploadFlag;

    @ApiModelProperty(value = "性别")
    private String gender;
	/**民族*/

    @ApiModelProperty(value = "民族")
    private String nation;
	/**身份证号*/

    @ApiModelProperty(value = "身份证号")
    private String idcardNumber;
	/**出生日期*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
	/**年龄*/

    @ApiModelProperty(value = "年龄")
    private String age;
	/**籍贯*/

    @ApiModelProperty(value = "籍贯")
    private String jg;
	/**户籍地址*/

    @ApiModelProperty(value = "户籍地址")
    private String hjAddress;
	/**家庭地址*/

    @ApiModelProperty(value = "家庭地址")
    private String jtAddress;
	/**手机号码*/

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;
	/**单位*/

    @ApiModelProperty(value = "单位")
    private String unit;
	/**部门*/

    @ApiModelProperty(value = "部门")
    private String department;
	/**警员类型*/

    @ApiModelProperty(value = "警员类型")
    private String policeType;
	/**警员状态*/

    @ApiModelProperty(value = "警员状态")
    private String policeStatus;
	/**照片*/

    @ApiModelProperty(value = "照片")
    private String photo;
	/**删除标识*/

    @ApiModelProperty(value = "删除标识")
    private String delFlag;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}

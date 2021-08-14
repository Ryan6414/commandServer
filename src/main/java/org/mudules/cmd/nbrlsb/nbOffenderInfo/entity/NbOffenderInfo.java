package org.mudules.cmd.nbrlsb.nbOffenderInfo.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: nb_offender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Data
@TableName("nb_offender_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="nb_offender_info对象", description="nb_offender_info")
public class NbOffenderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**编号*/

    @ApiModelProperty(value = "编号")
    private String serialNumber;
	/**档案号*/

	private String uploadFlag;

    @ApiModelProperty(value = "档案号")
    private String recordNumber;
	/**姓名*/

    @ApiModelProperty(value = "姓名")
    private String name;
	/**性别*/

    @ApiModelProperty(value = "性别")
    private String gender;
	/**民族*/

    @ApiModelProperty(value = "民族")
    private String nation;
	/**身份证号*/

    @ApiModelProperty(value = "身份证号")
    private String idCardNumber;
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
	/**逮捕机关*/

    @ApiModelProperty(value = "逮捕机关")
    private String arrestJg;
	/**逮捕日期*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "逮捕日期")
    private Date arrestDate;
	/**判决字号*/

    @ApiModelProperty(value = "判决字号")
    private String pjzh;
	/**判决机关*/

    @ApiModelProperty(value = "判决机关")
    private String pjjg;
	/**判决日期*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "判决日期")
    private Date pjrq;
	/**罪名*/

    @ApiModelProperty(value = "罪名")
    private String charge;
	/**刑期起日*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "刑期起日")
    private Date xqStart;
	/**刑期止日*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "刑期止日")
    private Date xqEnd;
	/**剥政年限*/

    @ApiModelProperty(value = "剥政年限")
    private String bznx;
	/**入监日期*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入监日期")
    private Date rjrq;
	/**调入日期*/

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "调入日期")
    private Date drrq;
	/**收押类别*/

    @ApiModelProperty(value = "收押类别")
    private String syType;
	/**对别*/

    @ApiModelProperty(value = "对别")
    private String db;
	/**分管等级*/

    @ApiModelProperty(value = "分管等级")
    private String fgClass;
	/**在押状态*/

    @ApiModelProperty(value = "在押状态")
    private String zyStatus;
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

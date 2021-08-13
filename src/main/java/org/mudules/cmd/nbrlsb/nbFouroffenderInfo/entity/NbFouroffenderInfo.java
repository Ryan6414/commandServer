package org.jeecg.modules.netboat.nbFouroffenderInfo.entity;

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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: nb_fouroffender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Data
@TableName("nb_fouroffender_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="nb_fouroffender_info对象", description="nb_fouroffender_info")
public class NbFouroffenderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private String serialNumber;
	/**档案号*/
	@Excel(name = "档案号", width = 15)
    @ApiModelProperty(value = "档案号")
    private String recordNumber;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**性别*/
    @Dict(dicCode = "sex")
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private String gender;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String nation;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private String idCardNumber;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private String age;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
    private String jg;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
    private String hjAddress;
	/**家庭住址*/
	@Excel(name = "家庭住址", width = 15)
    @ApiModelProperty(value = "家庭住址")
    private String jtAddress;
	/**罪名*/
	@Excel(name = "罪名", width = 15)
    @ApiModelProperty(value = "罪名")
    private String charge;
	/**刑期起日*/
	@Excel(name = "刑期起日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "刑期起日")
    private Date xqStart;
	/**刑期止日*/
	@Excel(name = "刑期止日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "刑期止日")
    private Date xqEnd;
	/**剥政年限*/
	@Excel(name = "剥政年限", width = 15)
    @ApiModelProperty(value = "剥政年限")
    private String bznx;
	/**调入日期*/
	@Excel(name = "调入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "调入日期")
    private Date drrq;
	/**调入单位*/
	@Excel(name = "调入单位", width = 15)
    @ApiModelProperty(value = "调入单位")
    private String drdw;
	/**对别*/
	@Excel(name = "对别", width = 15)
    @ApiModelProperty(value = "对别")
    private String db;
	/**床位*/
	@Excel(name = "床位", width = 15)
    @ApiModelProperty(value = "床位")
    private String bed;
	/**病情*/
	@Excel(name = "病情", width = 15)
    @ApiModelProperty(value = "病情")
    private String illnessState;
	/**在押状态*/
    @Dict(dicCode = "zy_status")
	@Excel(name = "在押状态", width = 15)
    @ApiModelProperty(value = "在押状态")
    private String zyStatus;
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
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
    /**照片*/
    @Excel(name = "照片", width = 15)
    @ApiModelProperty(value = "照片")
    private String photo;
}

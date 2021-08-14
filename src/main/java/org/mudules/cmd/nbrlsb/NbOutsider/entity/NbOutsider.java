package org.mudules.cmd.nbrlsb.NbOutsider.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("nb_outsider")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)

public class NbOutsider implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    private String id;
	/**姓名*/

    private String name;
	/**年龄*/

    private String uploadFlag;

    private String age;
	/**性别*/

    private String gender;
	/**户籍*/

    private String hj;
	/**身份证号*/

    private String idNumber;
	/**驾驶证号码*/

    private String driverLicenseNo;
	/**所属单位名称*/

    private String unitName;
	/**所属单位地址*/

    private String unitAddress;
	/**所属单位负责人*/

    private String unitChargeman;
	/**人员类别（0：固定人员，1：临时人员）*/

    private String type;
	/**照片*/

    private String photo;
	/**删除标识*/

	private Integer upload;

    private String delFlag;
	/**创建人*/

    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private Date createTime;
	/**更新人*/

    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private Date updateTime;
    /**服务项目*/

    private String serviceProject;
    /**申办进出狱内开始时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private Date startTime;
    /**申办进出狱内结束时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private Date endTime;
}

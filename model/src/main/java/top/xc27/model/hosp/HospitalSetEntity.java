package top.xc27.model.hosp;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import top.xc27.model.base.BaseEntity;

/**
 * 医院设置表
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-02-23 11:03:17
 */
@Data
@TableName("hospital_set")
@EqualsAndHashCode(callSuper = true)
public class HospitalSetEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hosName;
    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    private String hosCode;
    /**
     * api基础路径
     */
    @ApiModelProperty(value = "api基础路径")
    private String apiUrl;
    /**
     * 签名秘钥
     */
    @ApiModelProperty(value = "签名秘钥")
    private String signKey;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contactsName;
    /**
     * 联系人手机
     */
    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}

package top.xc27.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Schedule")
public class ScheduleQueryVo {

    @ApiModelProperty(value = "医院编号")
    private String hoscode;

    @ApiModelProperty(value = "科室编号")
    private String depcode;

    @ApiModelProperty(value = "医生编号")
    private String doccode;

    @ApiModelProperty(value = "安排日期")
    private Date workDate;

    @ApiModelProperty(value = "安排时间（0：上午 1：下午）")
    private Integer workTime;

    /**
     * 页数
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer page = 1;
    /**
     * 每页多少条
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer pageSize = 10;

}
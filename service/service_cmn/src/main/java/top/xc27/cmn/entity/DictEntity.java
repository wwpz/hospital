package top.xc27.cmn.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import top.xc27.model.base.BaseEntity;

/**
 * 组织架构表
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-03-02 16:29:30
 */
@Data
@TableName("dict")
@EqualsAndHashCode(callSuper = true)
public class DictEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 上级id
     */
    @ApiModelProperty(value = "上级id")
    private Integer parentId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private Integer value;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String dictCode;
    /**
     * 是否包含子节点
     */
    @ApiModelProperty(value = "是否包含子节点")
    @TableField(exist = false)
    private boolean hasChildren;
}

package top.xc27.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictEeVo {

    /**
     * id
     */
    @ExcelProperty(value = "id", index = 0)
    private Integer id;
    /**
     * 上级id
     */
    @ExcelProperty(value = "parentId", index = 1)
    private Integer parentId;
    /**
     * 名称
     */
    @ExcelProperty(value = "name", index = 2)
    private String name;
    /**
     * 值
     */
    @ExcelProperty(value = "value", index = 3)
    private Integer value;
    /**
     * 编码
     */
    @ExcelProperty(value = "dictCode", index = 4)
    private String dictCode;

    @ExcelProperty(value = "createTime", index = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ExcelProperty(value = "updateTime", index = 6)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ExcelProperty(value = "isDeleted", index = 7)
    private Integer isDeleted;
}

package top.xc27.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import top.xc27.common.result.ResultCode;

@Data
@ApiModel(value = "自定义全局异常类")
public class HospitalException extends RuntimeException{

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public HospitalException( Integer code,String message) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public HospitalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    @Override
    public String toString() {
        return "HospitalException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}

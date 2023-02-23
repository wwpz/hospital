package top.xc27.common.result;

import lombok.Getter;

/**
 * 错误码定义规则为5为数字
 * 前两位表示业务场景，最后三位表示错误码
 * 10 业务通用
 * 11 system模块
 */
@Getter
public enum ResultCode {

    /* 通用状态码 */
    SUCCESS(200, "请求成功!" ),
    ERROR(-9999,"请求失败!"),
    NOT_FOUND(404, "请求的资源不存在!"),
    SERVER_ERROR(500, "服务器内部错误!"),

    /* 业务通用状态码 */
    PRIMARY_EXCEPTION(10001,"主键缺失!"),
    PARAMETER_EXCEPTION(10002, "参数校验异常!"),
    QUERYENTITY_EXCEPTION(10003, "没有查询到对应数据!"),
    ;

    private final Integer code;
    private final String message;

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
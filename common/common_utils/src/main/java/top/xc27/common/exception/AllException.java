package top.xc27.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultCode;

@ControllerAdvice
public class AllException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> exception(Exception exception){
        exception.printStackTrace();
        return Result.fail(ResultCode.SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result<String> assertion(IllegalArgumentException exception){
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler(HospitalException.class)
    @ResponseBody
    public Result<String> hospitaltion(HospitalException exception){
        exception.printStackTrace();
        return Result.fail(exception.getCode(),exception.getMessage());
    }
}

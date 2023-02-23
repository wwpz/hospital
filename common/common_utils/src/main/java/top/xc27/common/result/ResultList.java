package top.xc27.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class ResultList<T> {

    private Integer code;
    private String message;
    private int total = 0;
    private Collection<T> objects;

    private ResultList(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    private ResultList(IPage<T> page, ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.objects = page.getRecords();
        this.total = (int) page.getTotal();
    }

    private ResultList(Collection<T> objects, ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.objects = objects;
    }

    public static <T> ResultList<T> success(IPage<T> page) {
        return new ResultList<>(page, ResultCode.SUCCESS);
    }

    public static <T> ResultList<T> success(Collection<T> objects) {
        return new ResultList<>(objects, ResultCode.SUCCESS);
    }

    public static <T> ResultList<T> fail() {
        return new ResultList<>(ResultCode.ERROR);
    }
}

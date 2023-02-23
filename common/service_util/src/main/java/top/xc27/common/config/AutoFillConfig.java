package top.xc27.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共字段，自动填充值
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-02-23 17:37:02
 */
@Component
public class AutoFillConfig implements MetaObjectHandler {
    private final static String CREATE_Time = "createTime";
    private final static String UPDATE_Time = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        //创建时间
        strictInsertFill(metaObject, CREATE_Time, LocalDateTime.class, LocalDateTime.now());
        //更新时间
        strictInsertFill(metaObject, UPDATE_Time, LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时间
        strictUpdateFill(metaObject, UPDATE_Time, LocalDateTime.class, LocalDateTime.now());
    }
}
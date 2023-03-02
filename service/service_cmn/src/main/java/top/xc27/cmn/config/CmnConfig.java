package top.xc27.cmn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("top.xc27.cmn.dao")
public class CmnConfig {
}

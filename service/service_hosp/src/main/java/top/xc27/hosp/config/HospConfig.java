package top.xc27.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("top.xc27.hosp.dao")
public class HospConfig {
}

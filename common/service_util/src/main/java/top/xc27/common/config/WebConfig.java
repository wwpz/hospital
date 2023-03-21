package top.xc27.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    // JsonInclude全局配置
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().serializationInclusion(JsonInclude.Include.NON_EMPTY);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

//    // 解决全局跨域问题
//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowCredentials(false).allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").allowedOrigins("*");
//    }

    // 配置拦截器放行Swagger2
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        /** 配置swagger2 显示文档 */
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        /** 配置swagger-ui显示文档 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

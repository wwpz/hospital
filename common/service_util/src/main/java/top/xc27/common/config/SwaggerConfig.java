package top.xc27.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Knife4j 3.X 配置类.
 * @author Pcling
 * @date 2023-2-27 23:43:39
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
// 在 Swagger 3.X 以下版本报错时可以加此注解解决，但是在3.X版本以上的，加此注解会导致页面无法打开
//@EnableWebMvc
public class SwaggerConfig {

    private static final String SWAGGER_TITLE = "HOSPITAL 项目 API 接口文档";
    private static final String VERSION = "3.0.3";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                // .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName("3.X 版本")
                .select()
                // 方式一: 配置扫描 所有想在swagger界面的统一管理接口，都必须在此包下
//                .apis(RequestHandlerSelectors.basePackage("com.dake.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // 方式二: 只有当方法上有  @ApiOperation 注解时才能生成对应的接口文档
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SwaggerConfig.SWAGGER_TITLE)
                .description("hospital项目API接口文档简介")
                .termsOfServiceUrl("http://127.0.0.1/#/login")
                .contact(new Contact("PcLing", "", "ok@126.com"))
                .version(SwaggerConfig.VERSION)
                .build();
    }

    // 不知道干嘛的
    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(),             "handlerMappings");
                    assert field != null;
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

}

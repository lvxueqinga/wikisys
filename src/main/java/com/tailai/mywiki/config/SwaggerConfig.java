package com.tailai.mywiki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 16:22
 *
 * swaggerui 的配置文件
 * 访问地址： http://localhost:8888/swagger-ui.html
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder().title("接口文档")
                .contact(new Contact("tailai","","123@qq.com"))
                .description("这是我的swaggerui生成的接口文档")
                .version("1.0.0.0")
                .build();

    }
}

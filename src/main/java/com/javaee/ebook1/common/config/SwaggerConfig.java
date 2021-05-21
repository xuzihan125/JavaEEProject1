package com.javaee.ebook1.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/19
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createAdminDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javaee.ebook1.controller.admin"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(builder())
                .groupName("管理员接口")
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket createAuthDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javaee.ebook1.controller.auth"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .groupName("登录授权接口")
                .globalOperationParameters(builder());
    }

    @Bean
    public Docket createEbookDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javaee.ebook1.controller.ebook"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .groupName("图书查看接口")
                .globalOperationParameters(builder());
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("javaee课程ebbok项目程序接口文档")
                .termsOfServiceUrl("javaee")
                .version("1.0")
                .build();
    }

    private List<Parameter> builder(){
        java.util.List<springfox.documentation.service.Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization").description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("Bearer ").build();
        pars.add(tokenPar.build());
        return pars;
    }
}

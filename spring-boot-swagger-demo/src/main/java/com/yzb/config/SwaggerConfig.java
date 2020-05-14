package com.yzb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String environment;

    /*
     * 文档描述信息配置
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo("标题：springboot整合swagger文档",
                "描述信息",
                "版本号1.0",
                "服务条款",
                new Contact("作者名", "网站地址", "email地址"),
                "开源版本号",
                "开源信息地址",
                new ArrayList<>()
        );
    }

    @Bean
    public Docket docket() {
        //如果当前发布环境为生产环境，则关闭Swagger，否则开启Swagger
        boolean flag;
        if (environment.equals("prod")){
            flag = false;
        }
        else {
            flag = true;
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .apiInfo(apiInfo())
                .select()
//               .apis(RequestHandlerSelectors.any()) //扫描全部包
//               .apis(RequestHandlerSelectors.none()) //全部不扫描
//               apis()方法用于配置扫描区域
                .apis(RequestHandlerSelectors.basePackage("com.yzb.controller")) //扫描此包下的类的接口
//               .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class)) //扫描类上有Controller注解的
//               .apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class)) //扫描方法上有@RequestMapping注解的
//              paths()方法用于路径过滤
                .paths(PathSelectors.any()) //扫描所有接口
//               .paths(PathSelectors.none()) //不扫描接口
                .paths(PathSelectors.ant("/**")) //路径匹配则扫描
//               .paths(PathSelectors.regex("")) //正则表达式匹配扫描符合的接口
                .build()
                ;
    }

}

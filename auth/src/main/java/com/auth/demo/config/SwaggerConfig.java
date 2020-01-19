package com.auth.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig 配置
 * @author Chunming_Wang
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private Contact contact = new Contact("Wacher","localhost:8080/swagger-ui.html", "sam425@163.com");

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.auth.demo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
                //.globalOperationParameters(globalOperation())
                //.securitySchemes(security())
                //.securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("平台接口 v1.0")
                .description("平台接口")
                .contact(contact)
                .version("1.0")
                .build();
    }

    /**
     * 方法一  设置全局token
     */
    private List<ApiKey> security() {
        List<ApiKey> apiKeys = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        apiKeys.add(apiKey);
        return apiKeys;
    }

    /**
     * 方法二  接口单独传token
     */
    private List<Parameter> globalOperation() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(tokenPar.build());
        return parameters;
    }

    /**
     * 通过正则表达式,去除掉不需要使用参数的接口
     * 所有包含"auth"的接口不需要使用securitySchemes
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

}

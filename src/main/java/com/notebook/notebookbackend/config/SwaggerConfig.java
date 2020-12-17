package com.notebook.notebookbackend.config;

import com.notebook.notebookbackend.utils.TokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;


/**
 * @author 22454
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket controllerApi() {
        ParameterBuilder tokenParameter = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        tokenParameter.name(TokenUtil.getTokenHeader()).description("use token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameterList.add(tokenParameter.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("API list and test")
                        .description("NoteBook-API文档")
                        .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
                        .version("1.0.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.notebook.notebookbackend"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameterList);
    }
}
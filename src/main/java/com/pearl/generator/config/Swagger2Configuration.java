package com.pearl.generator.config;

import com.pearl.generator.java.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangDan
 * @version 1.0
 * @since 2022/7/7
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Configuration {
    /**
     * 文档
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessages = buildResponseMessageList();
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .apiInfo(apiInfo("代码生成器", "明珠科技有限公司", "1.0.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pearl.generator")) // 扫描路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 文档信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo(String title, String description, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }

    /**
     * 配置 Authorization 消息头
     *
     * @return List<ApiKey>
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey("Authorization", "Authorization", "header"));
        return list;
    }

    /**
     * 构建响应状态信息描述
     *
     * @return List<ResponseMessage>
     */
    private List<ResponseMessage> buildResponseMessageList() {
        List<Example> okExampleList = buildOkExampleList();
        List<Example> failedExampleList = buildFailedExampleList();
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).examples(okExampleList).message("请求成功").responseModel(new ModelRef("R")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).examples(failedExampleList).message("客户端错误请求").responseModel(new ModelRef("R")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).examples(failedExampleList).message("请求未授权").responseModel(new ModelRef("R")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).examples(failedExampleList).message("服务器内部错误").responseModel(new ModelRef("R")).build());
        return responseMessageList;
    }

    private List<Example> buildOkExampleList() {
        Result<String> okResult = Result.success("响应数据");
        List<Example> exampleList = new ArrayList<>();
        Example example200 = new Example(MediaType.APPLICATION_JSON_VALUE, okResult);
        exampleList.add(example200);
        return exampleList;
    }

    private List<Example> buildFailedExampleList() {
        Result<Object> failedResult = Result.fail();
        List<Example> exampleList = new ArrayList<>();
        Example exampleNo200 = new Example(MediaType.APPLICATION_JSON_VALUE, failedResult);
        exampleList.add(exampleNo200);
        return exampleList;
    }
}
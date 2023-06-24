package com.kestrel.weddingbookkeeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kestrel.weddingbookkeeper.controller"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("너에게난,나에게넌 명세서")
                .description("네어게난,나에게넌 API 명세서")
                .version("1.0")
                .build();
    }
}

//
//@Configuration  //스프링 실행시 설정파일 읽어드리기 위한 어노테이션
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/*/**"))
//                .build();
//    }
//
//    public ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("게시글 API")
//                .version("3.4")
//                .description("추삭수가 가능한 게시글 api 입니다잇!!")
//                .build();
//    }
//}
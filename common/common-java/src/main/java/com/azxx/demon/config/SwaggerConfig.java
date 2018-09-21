package com.azxx.demon.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Smile on 2018/9/http://www.isspark.com/reward/static/data/a.mp320.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
//    @Autowired
//    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
//    {
//        this.springSwaggerConfig = springSwaggerConfig;
//    }
    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
//    @Bean
//    public SwaggerSpringMvcPlugin customImplementation()
//    {
//        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//                .apiInfo(apiInfo())
//                .includePatterns(".*?");
//    }

//    private ApiInfo apiInfo()
//    {
//        ApiInfo apiInfo = new ApiInfo(
//                "springmvc搭建swagger",
//                "spring-API swagger测试",
//                "My Apps API terms of service",
//                "534560449@qq.com",
//                "web app",
//                "My Apps API License URL");
//        return apiInfo;
//    }



//    @Bean
//    public Docket userApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()//选择哪些路径和api会生成document
//                .apis(RequestHandlerSelectors.any())//对所有api进行监控
//                .paths(PathSelectors.any())//对所有路径进行监控
//                .build();
//    }
//
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监
                .build();
    }
    @Bean
    public RequestMappingInfoHandlerMapping requestMapping(){
        return new RequestMappingHandlerMapping();
    }
}

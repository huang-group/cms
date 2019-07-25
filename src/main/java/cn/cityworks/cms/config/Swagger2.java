package cn.cityworks.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Swagger2配置类
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 创建API应用
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("cn.cityworks.cms"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * 创建API的基本信息（这些信息会展示在文档页面中）
     * 访问地址：http://项目地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        //使用apiBuilder构建ApiInfo
        ApiInfoBuilder apiBuilder = new ApiInfoBuilder();
        apiBuilder.title("Swagger2自动化构建RESTful APIs");      //ApiInfo标题
        apiBuilder.description("自动化构建接口文档");        //ApiInfo描述
        apiBuilder.termsOfServiceUrl("https://www.baidu.com/");     //ApiInfo超时链接
        apiBuilder.version("1.0");      //ApiInfo版本

        return apiBuilder.build();
    }
}
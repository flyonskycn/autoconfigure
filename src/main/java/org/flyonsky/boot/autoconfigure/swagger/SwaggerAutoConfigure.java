package org.flyonsky.boot.autoconfigure.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConditionalOnClass({EnableSwagger2.class})
@EnableConfigurationProperties({SwaggerProperties.class})
@Configuration
public class SwaggerAutoConfigure {

    @Bean
    public Docket petApi(SwaggerProperties swagger) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swagger.getGroupName())
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .build();
    }
    
    private ApiInfo apiInfo(SwaggerProperties swagger) {
        return new ApiInfoBuilder()
                .version(swagger.getVersion())
                .build();
    }
}

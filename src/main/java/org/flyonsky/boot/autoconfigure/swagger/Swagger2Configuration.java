package org.flyonsky.boot.autoconfigure.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@Configuration
@ConditionalOnProperty(name = SwaggerConstants.SWAGGER_ENABLED, matchIfMissing = true)
@Import({Swagger2DocumentationConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class Swagger2Configuration {

}

package io.murad.blog.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerAPIDocumentationConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/*"))
                .apis(RequestHandlerSelectors.basePackage("io.murad"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "CMS Blog REST-API",
                "Complete API for CMS-Blog",
                "1.0",
                "Free to use",
                new Contact("Md Murad Hossain", "https://github.com/mdmuradhossain", "murad.hossain.cse@ulab.edu.bd"),
                "API License",
                "https://github.com/mdmuradhossain",
                Collections.emptyList()
        );
    }
}

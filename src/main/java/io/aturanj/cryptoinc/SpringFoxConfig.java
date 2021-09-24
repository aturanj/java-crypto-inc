package io.aturanj.cryptoinc;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket postsApi() {

        ApiInfo apiInfo = new ApiInfo("Cyrpto Inc.", "Orders API", "API", "Terms of service",
                new Contact("", "http://localhost:8080/swagger-ui/", ""), "Apache License 2.0", "https://www.apache.org/licenses/", Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.aturanj.cryptoinc"))
                .paths(regex("/*.*"))
                .build()
                .apiInfo(apiInfo);
    }
}

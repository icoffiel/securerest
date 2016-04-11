package com.nerdery.icoffiel.securerest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by icoffiel on 4/8/2016.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Bean
    public Docket swaggerSpringFoxDocket(MyAppProperties myAppProperties) {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();

        Contact contact = new Contact(
                myAppProperties.getSwagger().getContactName(),
                myAppProperties.getSwagger().getContactUrl(),
                myAppProperties.getSwagger().getContactEmail());

        ApiInfo apiInfo =  new ApiInfo(
                myAppProperties.getSwagger().getTitle(),
                myAppProperties.getSwagger().getDescription(),
                myAppProperties.getSwagger().getVersion(),
                myAppProperties.getSwagger().getTermsOfServiceUrl(),
                contact,
                myAppProperties.getSwagger().getLicense(),
                myAppProperties.getSwagger().getLicenseUrl()
        );

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                    .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                    .build();

        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}

package com.mymovie.api.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI getOpenAPI() {
        var securityRequirement = new SecurityRequirement().addList(SECURITY_SCHEME_NAME);

        var securityComponents = new Components().addSecuritySchemes(
                SECURITY_SCHEME_NAME,
                buildSecurityScheme()
        );

        return new OpenAPI()
                .info(buildInfo())
                .addSecurityItem(securityRequirement)
                .components(securityComponents);
    }

    private Info buildInfo() {
        return new Info()
                .title("MyMovie API")
                .version("1.0")
                .description("API for managing movies and categories with user authentication.")
                .contact(new Contact()
                        .name("Cleiner")
                        .url("https://github.com/cleinerFG"));
    }

    private SecurityScheme buildSecurityScheme() {
        return new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}

package com.mromer.customer_microservice.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final SwaggerProperties swaggerProperties;

    OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info().
                title("Customer Microservice API")
                .description("Customer Microservice API documentation")
                .version("v1.0.0"))
            .servers(List.of(new Server().url(swaggerProperties.getApiGatewayUrl())));
    }
}

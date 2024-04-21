package com.josiasmartins.spring_security_jwt_oauth2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Tweet simplist")
                        .description("API Rest Tweet")
                        .contact(new Contact()
                                .name("Time back end")
                                .email("josiasmartinscaitano@gmail.com")
                        )
                        .license(new License()
                                .name("MIT")
                                .url("https://mit")
                        )
                )
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                ))
                .tags(List.of(new Tag()
                        .name("api_rest")
                        .description("api rest full")
                ));
    }


}

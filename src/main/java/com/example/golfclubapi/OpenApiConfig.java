package com.example.golfclubapi.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI golfClubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Golf Club Tournament & Membership API")
                        .description("API for managing golf club members, tournaments, and registrations.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jaowad Hossain")
                                .email("jaowad.hossain@keyin.com")  // real email
                        )
                );
    }
}

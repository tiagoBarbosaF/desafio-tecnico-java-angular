package com.tiagobarbosa.backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de consulta de créditos")
                        .description("API Rest para consulta de créditos constituídos por NFS-e ou número do crédito")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Tiago Barbosa")
                                .email("tiagobfarias@outlook.com")
                        ));
    }
}

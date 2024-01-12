package com.socialmedia.coreapi.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    @Value('${com.socialmedia.coreapi.url}')
    private String selfUrl

    @Value('${com.socialmedia.auth.url}')
    private String authUrl

    @Bean
    OpenAPI myOpenAPI() {
        Server selfServer = new Server()
        selfServer.setUrl(selfUrl)
        selfServer.setDescription("Social media core api service")

        Server authServer = new Server()
        authServer.setUrl(authUrl)
        authServer.setDescription("Authentication server")

        Contact contact = new Contact()
        contact.setEmail("ciprian.nicuta.dev@gmail.com")
        contact.setName("Ciprian")

        Info info = new Info()
                .title("Socialmedia Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage socialmedia project.")


        new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .servers(List.of(selfServer, authServer))
    }

    private static SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer")
    }
}
package com.socialmedia.coreapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    @Value('${com.socialmedia.coreapi.devUrl}')
    private String devUrl

    @Value('${com.socialmedia.coreapi.prodUrl}')
    private String prodUrl

    @Bean
     OpenAPI myOpenAPI() {
        Server devServer = new Server()
        devServer.setUrl(devUrl)
        devServer.setDescription("Server URL in Development environment")

        Server prodServer = new Server()
        prodServer.setUrl(prodUrl)
        prodServer.setDescription("Server URL in Production environment")

        Contact contact = new Contact()
        contact.setEmail("ciprian.nicuta.dev@gmail.com")
        contact.setName("Ciprian")

        Info info = new Info()
                .title("Socialmedia Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage socialmedia project.")


        new OpenAPI().info(info).servers(List.of(devServer, prodServer))
    }
}
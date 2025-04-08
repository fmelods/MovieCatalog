package br.com.fiap.moviecatalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor Local"),
                        new Server().url("https://api.moviecatalog.fiap.com.br").description("Servidor de Produção")
                ))
                .info(new Info()
                        .title("Movie Catalog API - FIAP")
                        .version("1.0.0")
                        .description("""
                                API RESTful para gerenciamento de catálogo de filmes.
                                Desenvolvida como parte do curso FIAP.
                                """)
                        .termsOfService("https://fiap.com.br/terms")
                        .contact(new Contact()
                                .name("Equipe FIAP")
                                .email("movie-catalog@fiap.com.br")
                                .url("https://fiap.com.br/support"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
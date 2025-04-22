package site.nansan.BASA_M.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI EnServiceOpenAPI() {

        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("https://nansan.site/api/v1/m")
                                .description("M-Service Swagger Document")
                ))
                .components(new Components()
                        .addSecuritySchemes("X-User-Id",
                                new SecurityScheme()
                                        .name("X-User-Id")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("X-User-Nickname",
                                new SecurityScheme()
                                        .name("X-User-Nickname")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("X-User-Role",
                                new SecurityScheme()
                                        .name("X-User-Role")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER))
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("X-User-Id")
                        .addList("X-User-Nickname")
                        .addList("X-User-Role")
                )
                .info(new Info()
                        .title("Nansan: M Service API")
                        .description("M Service에 대한 API List")
                        .version("v1")
                );
    }
}

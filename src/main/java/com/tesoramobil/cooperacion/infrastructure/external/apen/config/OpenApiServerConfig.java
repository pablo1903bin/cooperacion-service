package com.tesoramobil.cooperacion.infrastructure.external.apen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiServerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    // Base público detrás del gateway (sin host, para que herede https://supernovaworks.com.mx)
    return new OpenAPI()
        .servers(List.of(new Server().url("/gateway/cooperacion-service")));
  }
}

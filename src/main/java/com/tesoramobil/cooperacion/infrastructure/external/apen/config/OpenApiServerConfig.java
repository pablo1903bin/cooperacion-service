package com.tesoramobil.cooperacion.infrastructure.external.apen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiServerConfig {
	
  @Value("${api-doc.server-base-path:/cooperacion-service}")
  private String serverBasePath;
	  
  @Bean
  public OpenAPI customOpenAPI() {

    return new OpenAPI()
        .servers(List.of(new Server().url(serverBasePath)));
  }
}

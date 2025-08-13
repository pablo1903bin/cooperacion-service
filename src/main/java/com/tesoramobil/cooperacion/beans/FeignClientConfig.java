
package com.tesoramobil.cooperacion.beans;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor bearerAuthInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 🔐 Token fijo por ahora (o extraído de contexto)
                String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW0iLCJyb2xlcyI6IkFETUlOIiwiaWQiOjIsImlhdCI6MTc0OTgyOTA2NywiZXhwIjoxNzQ5ODMyNjY3fQ.1spzzBvv9qf_618d62Ph0vGo74NV_Kn3KjtLTXQFYfw8EweVhopEGj-eucyfYxBZxs6sDTkBlbApIbJPQkjA5g"; // Aquí el token real

                template.header("Authorization", "Bearer " + token);
            }
        };
    }
}

package com.tesoramobil.cooperacion.infrastructure.external.common.interceptor;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLoggingInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        System.out.println("➡️ Feign REQUEST:");
        System.out.println("📡 Method: " + template.method());
        System.out.println("🌐 URL Relativa: " + template.url());
        System.out.println("🧾 Headers: " + template.headers());
        
    }
}

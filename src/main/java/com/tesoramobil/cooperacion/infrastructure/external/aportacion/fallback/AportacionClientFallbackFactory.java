package com.tesoramobil.cooperacion.infrastructure.external.aportacion.fallback;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.tesoramobil.cooperacion.domain.model.Aportacion;
import com.tesoramobil.cooperacion.infrastructure.external.aportacion.client.AportacionFeignClient;
import com.tesoramobil.cooperacion.infrastructure.web.dto.ApiResponse;

//AportacionClientFallbackFactory.java
@Component
public class AportacionClientFallbackFactory implements FallbackFactory<AportacionFeignClient> {
 @Override
 public AportacionFeignClient create(Throwable cause) {
     return cooperacionId -> {
         // Aquí puedes loguear la causa concreta
         // log.error("Fallo aportacion-service", cause);
    	 return ApiResponse.<List<Aportacion>>builder()
    		        .codigo("ERROR")
    		        .mensaje("Fallo llamando a aportacion-service: " + cause.getClass().getSimpleName())
    		        .data(Collections.emptyList())
    		        .build();

     };
 }
}

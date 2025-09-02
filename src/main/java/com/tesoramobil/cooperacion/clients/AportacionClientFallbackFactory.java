package com.tesoramobil.cooperacion.clients;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.tesoramobil.cooperacion.dtos.Aportacion;
import com.tesoramobil.cooperacion.models.ApiResponse;

//AportacionClientFallbackFactory.java
@Component
public class AportacionClientFallbackFactory implements FallbackFactory<AportacionClient> {
 @Override
 public AportacionClient create(Throwable cause) {
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

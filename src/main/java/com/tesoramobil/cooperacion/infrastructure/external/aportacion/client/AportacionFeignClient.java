package com.tesoramobil.cooperacion.infrastructure.external.aportacion.client;

import com.tesoramobil.cooperacion.domain.model.Aportacion;
import com.tesoramobil.cooperacion.infrastructure.external.aportacion.fallback.AportacionClientFallbackFactory;
import com.tesoramobil.cooperacion.infrastructure.web.dto.ApiResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "aportacion-service", path = "/aportacion-service/aportaciones",   fallbackFactory = AportacionClientFallbackFactory.class)
//@FeignClient(name = "users-service", path = "/users-service/users")
public interface AportacionFeignClient {

    @GetMapping("/cooperacion/{cooperacionId}")
    ApiResponse<List<Aportacion>> obtenerPorCooperacion(@PathVariable("cooperacionId") Long cooperacionId);
}
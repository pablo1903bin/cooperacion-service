package com.tesoramobil.cooperacion.infrastructure.out.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "aportacion-service", path = "/aportacion-service/aportaciones")
@FeignClient(name = "aportacion-service", path = "/aportacion-service/aportaciones")
public interface AportacionClient {

    @GetMapping("/cooperacion/{cooperacionId}")
    ApiResponse<List<AportacionClientDto>> obtenerPorCooperacion(@PathVariable("cooperacionId") Long cooperacionId);
}
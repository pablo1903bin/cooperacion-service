package com.tesoramobil.cooperacion.clients;

import com.tesoramobil.cooperacion.dtos.Aportacion;
import com.tesoramobil.cooperacion.models.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "aportacion-service", path = "/aportacion-service/aportaciones")
//@FeignClient(name = "users-service", path = "/users-service/users")
public interface AportacionClient {

    @GetMapping("/cooperacion/{cooperacionId}")
    ApiResponse<List<Aportacion>> obtenerPorCooperacion(@PathVariable("cooperacionId") Long cooperacionId);
}
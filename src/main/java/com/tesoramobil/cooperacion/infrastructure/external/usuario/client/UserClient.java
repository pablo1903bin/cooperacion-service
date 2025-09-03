package com.tesoramobil.cooperacion.infrastructure.external.usuario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tesoramobil.cooperacion.dtos.UsuarioDto;
import com.tesoramobil.cooperacion.infrastructure.web.dto.ApiResponse;


@FeignClient(name = "users-service", url = "http://192.168.100.48:8088/users-service/users")
//@FeignClient(name = "users-service", path = "/users-service/users")
public interface UserClient {
    @GetMapping("/{id}")
    ApiResponse<UsuarioDto> findById(@PathVariable("id") Long id);
}

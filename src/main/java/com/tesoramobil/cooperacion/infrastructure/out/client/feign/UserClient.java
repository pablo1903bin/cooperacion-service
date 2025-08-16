package com.tesoramobil.cooperacion.infrastructure.out.client.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//@FeignClient(name = "users-service", url = "http://192.168.100.48:8088/users-service/users")
@FeignClient(name = "users-service", path = "/users-service/users")
public interface UserClient {

    @PostMapping("/info")
    ApiResponse<List<UsuarioClientDto>> getUsuariosByIds(@RequestBody List<Long> userIds);

}

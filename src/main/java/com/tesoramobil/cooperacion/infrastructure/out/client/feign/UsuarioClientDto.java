package com.tesoramobil.cooperacion.infrastructure.out.client.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuarioClientDto{

    private Long id;
    private String nombre;
    private String email;

 }
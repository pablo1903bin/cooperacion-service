package com.tesoramobil.cooperacion.infrastructure.out.client.feign;

import java.time.LocalDateTime;

import com.tesoramobil.cooperacion.infrastructure.in.web.dto.UsuarioDto;

import lombok.Data;


@Data
public class AportacionClientDto {
    private Long id;
    private Long cooperacionId;
    private Long usuarioId;
    private Double monto;
    private LocalDateTime fecha;
    private String observaciones;

    private UsuarioDto usuario;
}

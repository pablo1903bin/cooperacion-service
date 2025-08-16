package com.tesoramobil.cooperacion.infrastructure.in.web.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Aportacion {
    private Long id;
    private Long cooperacionId;
    private Long usuarioId;
    private Double monto;
    private LocalDateTime fecha;
    private String observaciones;

    private UsuarioDto usuario;

}

package com.tesoramobil.cooperacion.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Aportacion {
    private Long id;
    private Long cooperacionId;
    private Long usuarioId;
    private Double monto;
    private LocalDateTime fecha;
    private String observaciones;
}

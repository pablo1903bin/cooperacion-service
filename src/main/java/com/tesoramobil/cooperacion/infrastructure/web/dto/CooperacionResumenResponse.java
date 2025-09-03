package com.tesoramobil.cooperacion.infrastructure.web.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CooperacionResumenResponse {
    private Long id;
    private String descripcion;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal montoActual;
    private BigDecimal montoObjetivo;
    private String nombre;
    private BigDecimal montoPorParticipante;
}
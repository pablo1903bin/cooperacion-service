package com.tesoramobil.cooperacion.infrastructure.in.web.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de respuesta mínima para Cooperación.
 * Pensado para exponer en la capa de entrada (Controller)
 * siguiendo arquitectura de puertos y adaptadores.
 */
@Value
@Builder
public class CooperacionResponse {

    Long id;
    String nombre;
    String descripcion;
    BigDecimal montoObjetivo;
    BigDecimal montoActual;
    LocalDate fechaInicio;
    LocalDate fechaFin;
}

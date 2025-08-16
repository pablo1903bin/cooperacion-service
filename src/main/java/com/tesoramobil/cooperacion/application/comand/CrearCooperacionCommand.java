package com.tesoramobil.cooperacion.application.comand;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Comando para crear una cooperación.
 * Usado en la capa de aplicación para ejecutar el caso de uso.
 * No tiene anotaciones de validación ni dependencias de frameworks web.
 */
@Value
@Builder
public class CrearCooperacionCommand {

    Long categoriaId;
    Long createdBy;
    String descripcion;
    LocalDateTime fechaCreacion;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    Long groupId;
    BigDecimal montoActual;
    BigDecimal montoObjetivo;
    BigDecimal montoRestante;
    String noCuentaPago;
    String nombre;
    LocalDateTime updateAt;
    Long updateBy;
    BigDecimal montoPorParticipante;
}

package com.tesoramobil.cooperacion.infrastructure.in.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
public class CrearCooperacionRequest {

  @NotNull(message = "categoriaId es obligatorio")
  @Positive(message = "categoriaId debe ser positivo")
  private Long categoriaId;

  @NotNull(message = "createdBy es obligatorio")
  @Positive(message = "createdBy debe ser positivo")
  private Long createdBy;

  @NotBlank(message = "descripcion es obligatoria")
  @Size(max = 255, message = "descripcion no debe exceder 255 caracteres")
  private String descripcion;

  @NotBlank(message = "bombre es obligatorio")
  @Size(max = 255, message = "bombre no debe exceder 255 caracteres")
  private String nombre;

  @NotNull(message = "fechaInicio es obligatoria")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaInicio;

  @NotNull(message = "fechaFin es obligatoria")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaFin;

  @NotNull(message = "groupId es obligatorio")
  @Positive(message = "groupId debe ser positivo")
  private Long groupId;

  @DecimalMin(value = "0.00", inclusive = true, message = "montoActual no puede ser negativo")
  @Digits(integer = 14, fraction = 2, message = "montoActual con formato inválido")
  private BigDecimal montoActual;

  @NotNull(message = "montoObjetivo es obligatorio")
  @DecimalMin(value = "0.01", inclusive = true, message = "montoObjetivo debe ser mayor a 0")
  @Digits(integer = 14, fraction = 2, message = "montoObjetivo con formato inválido")
  private BigDecimal montoObjetivo;

  @DecimalMin(value = "0.00", inclusive = true, message = "montoPorParticipante no puede ser negativo")
  @Digits(integer = 14, fraction = 2, message = "montoPorParticipante con formato inválido")
  private BigDecimal montoPorParticipante;

  // ----- Campos SOLO lectura (los llena el servidor). Opcional mantenerlos visibles en Response -----
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private BigDecimal montoRestante; // derivado en backend

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private java.time.LocalDateTime fechaCreacion;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private java.time.LocalDateTime updateAt;

  @Positive(message = "updateBy debe ser positivo")
  private Long updateBy; // opcional en creación (puede ser null)
}

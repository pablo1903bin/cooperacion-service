package com.tesoramobil.cooperacion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cooperacion {
  private Long id;

  // iguales al Command:
  private Long categoriaId;
  private Long createdBy;
  private String descripcion;

  private LocalDateTime fechaCreacion;  // la puedes setear en adapter/JPA @CreationTimestamp
  private LocalDate     fechaInicio;
  private LocalDate     fechaFin;

  private Long groupId;

  private BigDecimal montoActual;
  private BigDecimal montoObjetivo;
  private BigDecimal montoRestante;     // derivado o recibido (si viene null, lo calculamos)
  private BigDecimal montoPorParticipante;

  private String noCuentaPago;
  private String nombre;

  private LocalDateTime updateAt;       // la puedes setear en adapter/JPA @UpdateTimestamp
  private Long updateBy;

  private String estado;    
}

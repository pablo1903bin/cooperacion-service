package com.tesoramobil.cooperacion.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.tesoramobil.cooperacion.domain.model.Aportacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class CooperacionCompleta {
	
 private Long id;
 private String nombre;
 private String descripcion;
 private String estado;
 private LocalDate fechaInicio;
 private LocalDate fechaFin;
 private BigDecimal montoActual;
 private BigDecimal montoObjetivo;
 private BigDecimal montoRestante;
 private BigDecimal montoPorParticipante;
 private List<Aportacion> aportaciones;
 
}

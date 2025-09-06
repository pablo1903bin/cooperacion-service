package com.tesoramobil.cooperacion.infrastructure.web.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.tesoramobil.cooperacion.domain.model.Aportacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Cooperación “completa” para la UI
@Data 
@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class CooperacionCompletaResponse {
	
    private Long id;
    private Long categoriaId;
    private Long creadoPor;                 // createdBy
    private String nombre;
    private String descripcion;
    
    private String estado;                  // p.ej. ACTIVA, CERRADA
    private LocalDateTime fechaCreacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long grupoId;
    
    private BigDecimal montoActual;         // acumulado
    private BigDecimal montoObjetivo;
    private BigDecimal montoRestante;
    private BigDecimal montoPorParticipante;
    private String noCuentaPago;
    
    private LocalDateTime actualizadoEn;
    private Long actualizadoPor;
    
    private List<Aportacion> aportaciones;
    
}

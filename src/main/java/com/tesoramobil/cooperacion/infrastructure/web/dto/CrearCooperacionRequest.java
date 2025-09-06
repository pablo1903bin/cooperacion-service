package com.tesoramobil.cooperacion.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.tesoramobil.cooperacion.domain.model.Aportacion;

public record CrearCooperacionRequest(
		
	    Long categoriaId,
	    Long creadoPor,
	    String nombre,
	    String descripcion,
	    LocalDate fechaInicio,
	    LocalDate fechaFin,
	    Long grupoId,
	    BigDecimal montoObjetivo,
	    BigDecimal montoPorParticipante,
	    String noCuentaPago,
	    List<Aportacion> aportaciones
	    
	    
	    
	) {}

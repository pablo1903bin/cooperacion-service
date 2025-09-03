package com.tesoramobil.cooperacion.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CooperacionResponse(
		
	    Long id,
	    String nombre,
	    String descripcion,
	    String estado,
	    LocalDate fechaInicio,
	    LocalDate fechaFin,
	    BigDecimal montoActual,
	    BigDecimal montoObjetivo,
	    BigDecimal montoRestante,
	    BigDecimal montoPorParticipante
	    
	) {}

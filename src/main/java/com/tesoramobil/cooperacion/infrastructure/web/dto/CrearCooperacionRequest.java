package com.tesoramobil.cooperacion.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	    String noCuentaPago
	    
	) {}

package com.tesoramobil.cooperacion.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CooperacionResumeDto {
	

    private Long id;


    private String descripcion;


    private String estado;


    private LocalDate fechaInicio;


    private LocalDate fechaFin;


    private BigDecimal montoActual;


    private BigDecimal montoObjetivo;


    private String nombre;


    private BigDecimal  montoPorParticipante;
    
    

}

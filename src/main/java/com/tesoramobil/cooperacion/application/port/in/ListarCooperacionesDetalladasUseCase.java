package com.tesoramobil.cooperacion.application.port.in;

import java.util.List;

import com.tesoramobil.cooperacion.dtos.CooperacionConAportacionesDTO;



public interface ListarCooperacionesDetalladasUseCase {
	
	List<CooperacionConAportacionesDTO> ejecutar();
	
}

package com.tesoramobil.cooperacion.application.port.in;

import java.util.List;


import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionConAportacionesResponse;



public interface ListarCooperacionesDetalladasUseCase {
	
	List<CooperacionConAportacionesResponse> ejecutar();
	
}

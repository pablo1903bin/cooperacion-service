package com.tesoramobil.cooperacion.application.port.in;


import com.tesoramobil.cooperacion.domain.model.Cooperacion;

public interface CrearCooperacionUseCase {
	
    Cooperacion ejecutar(Cooperacion nueva);
    
}

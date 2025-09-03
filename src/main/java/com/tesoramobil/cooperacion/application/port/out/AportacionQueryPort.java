package com.tesoramobil.cooperacion.application.port.out;

import java.util.List;

import com.tesoramobil.cooperacion.dtos.Aportacion;

public interface AportacionQueryPort {
	
    List<Aportacion> obtenerPorCooperacion(Long cooperacionId);
    
    
}

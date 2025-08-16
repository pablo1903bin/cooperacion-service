package com.tesoramobil.cooperacion.application.service;

import com.tesoramobil.cooperacion.application.comand.CrearCooperacionCommand;

public interface CooperationService {

    Long crear(CrearCooperacionCommand cmd);
    
}
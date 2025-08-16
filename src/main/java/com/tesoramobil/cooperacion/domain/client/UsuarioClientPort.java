package com.tesoramobil.cooperacion.domain.client;

import java.util.List;

import com.tesoramobil.cooperacion.domain.model.UsuarioBasico;

public interface UsuarioClientPort {

  List<UsuarioBasico> getUsuariosByIds(List<Long> userIds);

}

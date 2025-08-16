package com.tesoramobil.cooperacion.domain.client;

import java.math.BigDecimal;

public interface AportacionClientPort {

  BigDecimal obtenerPorCooperacion(Long cooperacionId);
  
}

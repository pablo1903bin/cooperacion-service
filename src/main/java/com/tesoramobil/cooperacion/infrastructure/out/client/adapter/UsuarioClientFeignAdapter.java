package com.tesoramobil.cooperacion.infrastructure.out.client.adapter;

import org.springframework.stereotype.Component;

import com.tesoramobil.cooperacion.domain.client.UsuarioClientPort;
import com.tesoramobil.cooperacion.domain.model.UsuarioBasico;
import com.tesoramobil.cooperacion.infrastructure.out.client.feign.UserClient;
import com.tesoramobil.cooperacion.infrastructure.out.client.feign.UsuarioClientDto;

import java.util.Collections;
import java.util.List;

@Component
public class UsuarioClientFeignAdapter implements UsuarioClientPort {

  private final UserClient feign;

  public UsuarioClientFeignAdapter(UserClient feign) {
    this.feign = feign;
  }

  @Override
  public List<UsuarioBasico> getUsuariosByIds(List<Long> userIds) {

    var resp = feign.getUsuariosByIds(userIds);

    var data = (resp == null) ? null : resp.data();

    if (data == null) {
        return Collections.emptyList();
    }

    return data.stream()
        .map(this::toDomain)
        .toList();
  }

  private UsuarioBasico toDomain(UsuarioClientDto d) {
    return new UsuarioBasico(d.getId(), d.getNombre(), d.getEmail());
  }
}
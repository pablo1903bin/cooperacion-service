package com.tesoramobil.cooperacion.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

import com.tesoramobil.cooperacion.application.comand.CrearCooperacionCommand;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;

@Mapper(componentModel = "spring")
public interface CooperacionCommandMapper {

  @Mapping(target = "id", ignore = true)

  @Mapping(target = "categoriaId",        source = "categoriaId")
  @Mapping(target = "createdBy",          source = "createdBy")
  @Mapping(target = "descripcion",        source = "descripcion")

  @Mapping(target = "fechaCreacion",      source = "fechaCreacion")
  @Mapping(target = "fechaInicio",        source = "fechaInicio")
  @Mapping(target = "fechaFin",           source = "fechaFin")

  @Mapping(target = "groupId",            source = "groupId")

  @Mapping(target = "montoActual",        source = "montoActual",        qualifiedByName = "nz")
  @Mapping(target = "montoObjetivo",      source = "montoObjetivo",      qualifiedByName = "nz")
  @Mapping(target = "montoPorParticipante", source = "montoPorParticipante", qualifiedByName = "nz")

  // Si el request/command no manda 'montoRestante', lo calculamos: objetivo - actual
  @Mapping(
    target = "montoRestante",
    expression = "java( cmd.getMontoRestante() != null ? cmd.getMontoRestante() : " +
                 "(cmd.getMontoObjetivo() == null ? java.math.BigDecimal.ZERO : " +
                 " cmd.getMontoObjetivo().subtract(cmd.getMontoActual() == null ? java.math.BigDecimal.ZERO : cmd.getMontoActual())) )"
  )

  @Mapping(target = "noCuentaPago",       source = "noCuentaPago")
  @Mapping(target = "nombre",             source = "nombre")

  @Mapping(target = "updateAt",           source = "updateAt")
  @Mapping(target = "updateBy",           source = "updateBy")

  // Como el command no trae 'estado', fijamos un valor por defecto
  @Mapping(target = "estado",             constant = "activa")
  Cooperacion toDomain(CrearCooperacionCommand cmd);

  @Named("nz")
  default BigDecimal nz(BigDecimal v) {
    return v == null ? BigDecimal.ZERO : v;
  }
}
package com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.mapper;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.entity.CooperationEntity;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CooperacionJpaMapper {

  // Domain -> Entity
  @Mappings({
    @Mapping(target = "id",            source = "id"),
    @Mapping(target = "categoriaId",   source = "categoriaId"),
    @Mapping(target = "createdBy",     source = "createdBy"),
    @Mapping(target = "descripcion",   source = "descripcion"),
    @Mapping(target = "estado",        expression = "java( d.getEstado() != null ? d.getEstado() : \"activa\" )"),
    @Mapping(target = "fechaInicio",   source = "fechaInicio"),
    @Mapping(target = "fechaFin",      source = "fechaFin"),
    @Mapping(target = "groupId",       source = "groupId"),
    @Mapping(target = "noCuentaPago",  source = "noCuentaPago"),
    @Mapping(target = "nombre",        source = "nombre"),
    @Mapping(target = "updateBy",      expression = "java( d.getUpdateBy() != null ? d.getUpdateBy() : d.getCreatedBy() )"),
    @Mapping(target = "montoObjetivo", expression = "java( nz(d.getMontoObjetivo()) )"),
    @Mapping(target = "montoActual",   expression = "java( nz(d.getMontoActual()) )"),
    @Mapping(target = "montoPorParticipante", expression = "java( nz(d.getMontoPorParticipante()) )"),
    // timestamps los llena JPA/Hibernate
    @Mapping(target = "fechaCreacion", ignore = true),
    @Mapping(target = "updateAt",      ignore = true)
  })
  CooperationEntity toEntity(Cooperacion d);

  // Entity -> Domain
  @Mappings({
    @Mapping(target = "id",            source = "id"),
    @Mapping(target = "categoriaId",   source = "categoriaId"),
    @Mapping(target = "createdBy",     source = "createdBy"),
    @Mapping(target = "descripcion",   source = "descripcion"),
    @Mapping(target = "estado",        source = "estado"),
    @Mapping(target = "fechaInicio",   source = "fechaInicio"),
    @Mapping(target = "fechaFin",      source = "fechaFin"),
    @Mapping(target = "groupId",       source = "groupId"),
    @Mapping(target = "noCuentaPago",  source = "noCuentaPago"),
    @Mapping(target = "nombre",        source = "nombre"),
    @Mapping(target = "updateBy",      source = "updateBy"),
    @Mapping(target = "montoObjetivo", source = "montoObjetivo"),
    @Mapping(target = "montoActual",   source = "montoActual"),
    @Mapping(target = "montoPorParticipante", source = "montoPorParticipante"),
    @Mapping(target = "montoRestante", source = "montoRestante"),
    @Mapping(target = "fechaCreacion", source = "fechaCreacion"),
    @Mapping(target = "updateAt",      source = "updateAt")
  })
  Cooperacion toDomain(CooperationEntity e);

  // Completa 'montoRestante' si no viene
  @AfterMapping
  default void fillMontoRestante(Cooperacion d, @MappingTarget CooperationEntity e) {
    if (e.getMontoRestante() == null) {
      e.setMontoRestante( nz(e.getMontoObjetivo()).subtract( nz(e.getMontoActual()) ) );
    }
  }

  default BigDecimal nz(BigDecimal v) { return v == null ? BigDecimal.ZERO : v; }
}

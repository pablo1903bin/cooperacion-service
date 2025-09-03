package com.tesoramobil.cooperacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.tesoramobil.cooperacion.infrastructure.persistence.jpa.entity.CooperationEntity;

@Data
@AllArgsConstructor
public class CooperationConAportacionesDTO {

    private CooperationEntity cooperation;
    private List<Aportacion> aportaciones;

    
}

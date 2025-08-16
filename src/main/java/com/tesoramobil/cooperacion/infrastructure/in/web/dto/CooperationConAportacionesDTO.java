package com.tesoramobil.cooperacion.infrastructure.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.entity.CooperationEntity;

@Data
@AllArgsConstructor
public class CooperationConAportacionesDTO {

    private CooperationEntity cooperation;
    private List<Aportacion> aportaciones;

    
}

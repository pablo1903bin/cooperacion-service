package com.tesoramobil.cooperacion.dtos;

import com.tesoramobil.cooperacion.entities.CooperationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CooperationConAportacionesDTO {

    private CooperationEntity cooperation;
    private List<Aportacion> aportaciones;

    
}

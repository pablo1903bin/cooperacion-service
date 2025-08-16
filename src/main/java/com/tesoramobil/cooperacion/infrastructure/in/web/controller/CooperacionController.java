package com.tesoramobil.cooperacion.infrastructure.in.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tesoramobil.cooperacion.application.service.CooperationService;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.ApiResponse;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.CooperacionResponse;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.CrearCooperacionRequest;
import com.tesoramobil.cooperacion.infrastructure.in.web.mapper.CrearCooperacionMapper;

@RestController
@RequestMapping("/cooperations")
@Tag(name = "Cooperaciones", description = "Operaciones relacionadas con cooperaciones escolares o comunitarias")
public class CooperacionController {

    @Autowired
    CooperationService cooperationService;

    @Autowired
    private CrearCooperacionMapper mapper;

    @Operation(summary = "Crear una nueva cooperación")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperación creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<CooperacionResponse>> create(  @Valid @RequestBody CrearCooperacionRequest request) {

        // 1. Mapear Request → Command
        var cmd = mapper.toCommand(request);

        // 2. Pasar el command al servicio
        Long id = cooperationService.crear(cmd);

        // 3. Construir el DTO de respuesta
        var response = CooperacionResponse.builder()
                .id(id)
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .montoObjetivo(request.getMontoObjetivo())
                .montoActual(request.getMontoActual())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();

        // 4. Devolver respuesta HTTP con el DTO

        return ResponseEntity.ok(ApiResponse.ok("Cooperación creada", response));
    }

}

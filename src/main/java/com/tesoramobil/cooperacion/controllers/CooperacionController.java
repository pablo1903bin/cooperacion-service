package com.tesoramobil.cooperacion.controllers;

import com.tesoramobil.cooperacion.dtos.CooperacionResumeDto;
import com.tesoramobil.cooperacion.dtos.CooperationConAportacionesDTO;
import com.tesoramobil.cooperacion.entities.CooperationEntity;
import com.tesoramobil.cooperacion.models.ApiResponse;
import com.tesoramobil.cooperacion.services.CooperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cooperations")
@Tag(name = "Cooperaciones", description = "Operaciones relacionadas con cooperaciones escolares o comunitarias")
public class CooperacionController {

    @Autowired
    CooperationService cooperationService;

    @Operation(summary = "Obtener todas las cooperaciones resumidas")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de cooperaciones obtenida exitosamente")
    })
    @GetMapping("/getAll/resume")
    public ResponseEntity<ApiResponse<List<CooperacionResumeDto>>> findAll() {
        List<CooperacionResumeDto> list = cooperationService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("OK", "Cooperaciones encontradas", list));
    }

@Operation(summary = "Obtener una cooperación con sus aportaciones")
@ApiResponses({
     @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperación encontrada con aportaciones"),
     @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cooperación no encontrada")
})
@GetMapping("/{id}/detalle")
public ResponseEntity<ApiResponse<CooperationConAportacionesDTO>> findDetalleById(@PathVariable Long id) {
    return cooperationService.findDetalleById(id)
            .map(detalle -> ResponseEntity.ok(new ApiResponse<>("OK", "Cooperación con aportaciones", detalle)))
            .orElse(ResponseEntity.status(404)
                    .body(new ApiResponse<>("NOT_FOUND", "Cooperación no encontrada", null)));
}


    @Operation(summary = "Obtener una cooperación por su ID")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperación encontrada"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cooperación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CooperationEntity>> findById(@PathVariable Long id) {
        return cooperationService.findById(id)
                .map(cooperation -> ResponseEntity.ok(new ApiResponse<>("OK", "Cooperación encontrada", cooperation)))
                .orElse(ResponseEntity.status(404)
                        .body(new ApiResponse<>("NOT_FOUND", "Cooperación no encontrada", null)));
    }


    @Operation(summary = "Crear una nueva cooperación")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperación creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<CooperationEntity>> create(@RequestBody CooperationEntity cooperation) {
        CooperationEntity saved = cooperationService.save(cooperation);
        return ResponseEntity.ok(new ApiResponse<>("OK", "Cooperación creada exitosamente", saved));
    }


    @Operation(summary = "Actualizar una cooperación existente")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperación actualizada correctamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cooperación no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CooperationEntity>> update(@PathVariable Long id, @RequestBody CooperationEntity updated) {
        try {
            CooperationEntity result = cooperationService.update(id, updated);
            return ResponseEntity.ok(new ApiResponse<>("OK", "Cooperación actualizada correctamente", result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>("NOT_FOUND", e.getMessage(), null));
        }
    }


    @Operation(summary = "Eliminar una cooperación por ID")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperación eliminada correctamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cooperación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            cooperationService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>("OK", "Cooperación eliminada", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>("NOT_FOUND", e.getMessage(), null));
        }
    }


    @Operation(summary = "Obtener cooperaciones por ID de grupo")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperaciones del grupo encontradas")
    })
    @GetMapping("/grupo/{groupId}")
    public ResponseEntity<ApiResponse<List<CooperationEntity>>> findByGroupId(@PathVariable Integer groupId) {
        List<CooperationEntity> list = cooperationService.findByGroupId(groupId);
        return ResponseEntity.ok(new ApiResponse<>("OK", "Cooperaciones por grupo encontradas", list));
    }


    @Operation(summary = "Obtener cooperaciones activas por ID de grupo")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cooperaciones activas encontradas")
    })
    @GetMapping("/grupo/{groupId}/activas")
    public ResponseEntity<ApiResponse<List<CooperationEntity>>> findActiveByGroupId(@PathVariable Integer groupId) {
        List<CooperationEntity> list = cooperationService.findActiveByGroupId(groupId);
        return ResponseEntity.ok(new ApiResponse<>("OK", "Cooperaciones activas encontradas", list));
    }
}

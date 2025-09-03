package com.tesoramobil.cooperacion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data                                       // <-- getters/setters, equals/hashCode, toString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cooperacion {

    private Long id;
    private Long categoriaId;
    private Long creadoPor;                 // createdBy
    private String nombre;
    private String descripcion;
    
    private String estado;                  // p.ej. ACTIVA, CERRADA
    private LocalDateTime fechaCreacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long grupoId;
    
    private BigDecimal montoActual;         // acumulado
    private BigDecimal montoObjetivo;
    private BigDecimal montoRestante;
    private BigDecimal montoPorParticipante;
    private String noCuentaPago;
    
    private LocalDateTime actualizadoEn;
    private Long actualizadoPor;


    // ===== Reglas de negocio =====
    public void registrarAporte(BigDecimal monto, Long usuarioId) {
        notNegative(monto, "monto");
        if (!"ACTIVA".equals(estado)) throw new IllegalStateException("Cooperación no activa");
        this.montoActual = this.montoActual.add(monto);
        this.montoRestante = this.montoObjetivo.subtract(this.montoActual).max(BigDecimal.ZERO);
        this.actualizadoEn = LocalDateTime.now();
        this.actualizadoPor = usuarioId;
    }

    public boolean objetivoAlcanzado() {
        return montoActual.compareTo(montoObjetivo) >= 0;
    }

    public void cerrar(Long usuarioId) {
        if (!objetivoAlcanzado()) throw new IllegalStateException("No se puede cerrar sin alcanzar el objetivo");
        this.estado = "CERRADA";
        this.actualizadoEn = LocalDateTime.now();
        this.actualizadoPor = usuarioId;
    }

    // getters/setters

    // ===== Helpers =====
    private static String requireNonEmpty(String s, String field) {
        if (s == null || s.isBlank()) throw new IllegalArgumentException(field + " vacío");
        return s;
    }
    private static BigDecimal notNegative(BigDecimal v, String field) {
        if (v == null || v.signum() < 0) throw new IllegalArgumentException(field + " negativo");
        return v;
    }
}

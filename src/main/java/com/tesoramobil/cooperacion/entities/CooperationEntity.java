package com.tesoramobil.cooperacion.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tesora_cooperations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CooperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoria_id", nullable = false)
    private Integer categoriaId;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 255)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Column(name = "monto_actual")
    private Double montoActual;

    @Column(name = "monto_objetivo", nullable = false)
    private Double montoObjetivo;

    @Column(name = "monto_restante")
    private Double montoRestante;

    @Column(name = "no_cuenta_pago", length = 255)
    private String noCuentaPago;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_by", nullable = false)
    private Long updateBy;

    @Column(name = "monto_por_participante", nullable = false)
    private BigDecimal  montoPorParticipante;

}

package com.example.actividadesProgreso.Entity.Actividades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UsarActividad")
public class UsarActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private LocalDateTime inicio;
    @Column
    @NotNull
    private LocalDateTime fin;
    @Column(length = 5000)
    private String observacionesSalida;
    @Column(length = 5000)
    private String observacionesEntrega;
    @Column
    @NotNull
    private LocalDateTime fechaAlta = java.time.LocalDateTime.now();
    @Column
    private LocalDateTime fechaEntrega;
    @Column
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idActividad")
    @NotNull
    private ActividadEntity idActividad;

    @ManyToOne
    @JoinColumn(name = "idBienActividad")
    @NotNull
    private BienActividadEntity idBienActividad;
}

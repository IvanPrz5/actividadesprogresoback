package com.example.actividadesProgreso.Entity.Actividades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "BienActividad")
public class BienActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    @NotNull
    private String descripcion;
    @Column(length = 200)
    @NotNull
    private String icon;
    @Column(length = 100)
    private String tipo;
    @Column
    private Integer idEmpresa;
    @Column
    private Boolean status = true;
}

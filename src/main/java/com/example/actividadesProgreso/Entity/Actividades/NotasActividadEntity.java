package com.example.actividadesProgreso.Entity.Actividades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NotasActividad")
public class NotasActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 5000)
    @NotNull
    @NotBlank
    private String descripcion;
    @Column 
    private Boolean status = true;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idActividad")
    private ActividadEntity idActividad;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idUsuario")
    private UsuariosEntity idUsuario;
}

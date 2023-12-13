package com.example.actividadesProgreso.Entity.Actividades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "UsuarioActividad")
public class UsuarioActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUa;

    @Column
    private Boolean userActStatus = true;

    @ManyToOne
    @JoinColumn(name = "idActividad")
    private ActividadEntity idActividad;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuariosEntity idUsuario;
}
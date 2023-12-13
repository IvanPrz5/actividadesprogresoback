package com.example.actividadesProgreso.Entity.Actividades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ArchivoActividad")
public class ArchivoActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 300)
    private String nombre;
    @Column(length = 1000)
    private String ruta;
    @Column(length = 1000)
    private String url;
    @Column(length = 200)
    private String icon;
    @Column
    private Boolean status = true;
    @Column
    private LocalDateTime fechaSubida = java.time.LocalDateTime.now();
    @Column(length = 500)
    private String tipoArchivo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuariosEntity idUsuario;

    @ManyToOne
    @JoinColumn(name = "idActividad")
    private ActividadEntity idActividad;
    
    @ManyToOne
    @JoinColumn(name = "idArchivoActividad")
    @JsonBackReference
    private ArchivoActividadEntity idArchivoActividad;
}

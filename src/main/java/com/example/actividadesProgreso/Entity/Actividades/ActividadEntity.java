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

import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;
import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;
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
@Table(name = "Actividad")
public class ActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 5000)
    @NotNull
    private String descripcion;
    @Column 
    @NotNull
    private LocalDateTime fechaInicio;
    @Column 
    @NotNull
    private LocalDateTime fechaFin;
    @Column
    private LocalDateTime fechaFinalizoUser;
    @Column
    private Boolean isMensual = false;
    @Column
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idTipoActividad")
    @NotNull
    private TipoActividadEntity idTipoActividad;

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    @NotNull
    private EmpresasEntity idEmpresa;

    @ManyToOne
    @JoinColumn(name = "idStatusActividad")
    private StatusActividadEntity idStatusActividad;

    @ManyToOne
    @JoinColumn(name = "idUsuarioAsigno")
    @NotNull
    private UsuariosEntity idUsuarioAsigno;

    @ManyToOne
    @JoinColumn(name = "idUsuarioTermino")
    private UsuariosEntity idUsuarioTermino;

    @ManyToOne
    @JoinColumn(name = "idJerarquiaActividad")
    @NotNull
    private JerarquiaActividadEntity idJerarquiaActividad;

    @ManyToOne
    @JoinColumn(name = "idClientes")
    private ClientesEntity idClientes;
}

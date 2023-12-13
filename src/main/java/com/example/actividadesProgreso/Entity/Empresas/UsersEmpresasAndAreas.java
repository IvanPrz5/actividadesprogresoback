package com.example.actividadesProgreso.Entity.Empresas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.actividadesProgreso.Entity.Areas.AreasEntity;
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
@Table(name = "UsersEmpresasAndAreas")
public class UsersEmpresasAndAreas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuariosEntity idUsuario;

    @ManyToOne
    @JoinColumn(name="idArea")
    private AreasEntity idArea;

    @ManyToOne
    @JoinColumn(name="idEmpresa")
    private EmpresasEntity idEmpresa;
}

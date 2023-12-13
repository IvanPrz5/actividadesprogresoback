package com.example.actividadesProgreso.Entity.Menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.actividadesProgreso.Entity.Usuarios.RolesEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MenuRoles")
public class MenuAndRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 3)
    private Long orden;

    @ManyToOne
    @JoinColumn(name = "idRole")
    private RolesEntity idRole;

    @ManyToOne
    @JoinColumn(name = "idMenu")
    private MenuEntity idMenu;

    @ManyToOne
    @JoinColumn(name = "depende")
    private MenuAndRoleEntity depende;
}

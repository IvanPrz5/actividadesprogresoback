package com.example.actividadesProgreso.Entity.Usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="Usuarios")
public class UsuariosEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    @NotNull
    private String nombre;
    @Column(length = 50)
    @NotNull
    private String apPaterno;
    @Column(length = 100)
    @NotNull
    private String email;
    @Column(length = 50)
    @NotNull
    private Long telefono;
    @Column
    private Date fechaNacimiento = new Date();
    @Column
    @NotNull
    private String password;
    @Column(length = 500)
    private String icon = "https://cdn.vuetifyjs.com/images/cards/road.jpg";
    @Column(length = 1000)
    private String rutaAvatar;
    @Column
    private Boolean status = true;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RolesEntity> roles = new ArrayList<>();
}
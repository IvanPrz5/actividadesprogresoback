package com.example.actividadesProgreso.Entity.Usuarios.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.EmpresasInterface;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.IdAndDescripcionInterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDTO {
    private Long id;
    private String nombre;
    private String paterno;
    private String email;
    private String icon;
    private Long telefono;
    private String fechaNacimiento;
    private List<IdAndDescripcionInterface> roles = new ArrayList<>();
    private List<IdAndDescripcionInterface> areas = new ArrayList<>();
    private List<EmpresasInterface> empresas = new ArrayList<>();

    public UsuariosDTO(UsuariosEntity usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.paterno = usuario.getApPaterno();
        this.email = usuario.getEmail();
        this.icon = usuario.getIcon();
        this.telefono = usuario.getTelefono();
        this.fechaNacimiento = usuario.getFechaNacimiento().toString();
        /* this.roles.add(usuario.getIdRole().getId());
        this.roles.add(usuario.getIdRole().getDescripcion()); */
    }
}

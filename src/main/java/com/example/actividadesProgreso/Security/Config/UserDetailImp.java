package com.example.actividadesProgreso.Security.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.actividadesProgreso.Entity.Usuarios.RolesEntity;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImp implements UserDetails{

    private final UsuariosEntity usuariosEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RolesEntity> roles = usuariosEntity.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(RolesEntity role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getDescripcion()));
        }
        return authorities;
    }

    /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.toString()));
        return roles;
    } */

    @Override
    public String getPassword() {
        return usuariosEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return usuariosEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public String getNombre(){
        return usuariosEntity.getNombre();
    }

    public UsuariosEntity getUser() {
        return usuariosEntity;
    }

    public Long getId(){
        return usuariosEntity.getId();
    }
}

package com.example.actividadesProgreso.Security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService{
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuariosEntity usuariosEntity = usuariosRepository
        .findOneByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("El usuario con emal " + email + " no existe"));
        
        return new UserDetailImp(usuariosEntity);
    }
}

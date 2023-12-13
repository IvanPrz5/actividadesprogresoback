package com.example.actividadesProgreso.Service.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Usuarios.Interface.IdAndDescripcionInterface;
import com.example.actividadesProgreso.Repository.Usuarios.RolesRepository;

@Service
public class RolesService {
    @Autowired
    RolesRepository rolesRepository;

    public List<IdAndDescripcionInterface> getRolesByIdUsuario(Long id){
        try {
            List<IdAndDescripcionInterface> query = rolesRepository.getRoles(id);
            return query;
        } catch (Exception e) {
            return null;
        }
    }
}
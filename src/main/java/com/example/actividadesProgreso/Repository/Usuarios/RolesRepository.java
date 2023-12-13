package com.example.actividadesProgreso.Repository.Usuarios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Usuarios.RolesEntity;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.IdAndDescripcionInterface;

public interface RolesRepository extends JpaRepository<RolesEntity, Long>{
    @Query(value="SELECT r.id, r.descripcion FROM roles r INNER JOIN user_roles ur ON r.id = ur.role_id WHERE user_id = ?1 GROUP BY r.id", nativeQuery = true)
    List<IdAndDescripcionInterface> getRoles(Long id);
}

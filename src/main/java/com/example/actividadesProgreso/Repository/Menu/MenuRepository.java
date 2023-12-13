package com.example.actividadesProgreso.Repository.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Menu.MenuEntity;
import com.example.actividadesProgreso.Entity.Menu.Interface.MenuAndRoleInterface;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
    
    List<MenuEntity> findByIdAndStatus(Long id, Boolean status);
    
    @Query(value="SELECT m.id, m.descripcion, m.ruta, m.icon, mr.orden FROM menu m INNER JOIN menu_roles mr ON m.id = mr.id_menu WHERE mr.id_role = ?1 AND mr.depende IS null ORDER BY mr.orden", nativeQuery = true)
    List<MenuAndRoleInterface> getMenusByIdRol(Long id);

    @Query(value="SELECT m.id, m.descripcion, m.ruta, m.icon, mr.orden FROM menu m INNER JOIN menu_roles mr ON m.id = mr.id_menu WHERE mr.id_role = ?1 AND mr.depende = ?2 AND mr.depende IS NOT null ORDER BY mr.orden", nativeQuery = true)
    List<MenuAndRoleInterface> getSubMenusByIdRol(Long id, Long depende);
}   

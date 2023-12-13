package com.example.actividadesProgreso.Repository.Usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Actividades.Interface.UsuariosInterface;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;

public interface UsuariosRepository extends JpaRepository <UsuariosEntity, Long> {
    // Revisar Consulta
    @Query(value="SELECT DISTINCT id, icon, CONCAT(nombre, ' ', ap_paterno) AS descripcion FROM usuarios WHERE id IN (SELECT id_usuario from users_empresas_and_areas where id_empresa = ?1) AND status = true order by descripcion;" + //
            "", nativeQuery = true)
    List<UsuariosInterface> findByEmpresaQuery(Long idEmpresa);
    
    Optional<UsuariosEntity> findOneByEmail(String email);
    List<UsuariosEntity> findByIdAndStatus(Long id, Boolean status);
    
    @Query(value="SELECT DISTINCT us.id, us.icon, CONCAT(us.nombre, ' ', us.ap_paterno) AS descripcion FROM usuarios us " + 
    "INNER JOIN users_empresas_and_areas uea ON us.id = uea.id_usuario " + 
    "WHERE us.id not in (Select id_usuario FROM usuario_actividad WHERE id_actividad = ?1 AND user_act_status = true) AND uea.id_empresa = ?2 AND us.status = true order by descripcion;", nativeQuery = true)
    List<UsuariosInterface> findByIdActividadAndEmpresaQuery(Long idActividad, Long idEmpresa);

    @Modifying
    @Query(value = "INSERT INTO user_roles (user_id, role_id) VALUES (?1, ?2)", nativeQuery = true)
    List<UsuariosEntity>insertIntoUserRoles(Long idUser, Long idRole);
}
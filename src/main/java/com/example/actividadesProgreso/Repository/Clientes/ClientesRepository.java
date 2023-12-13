package com.example.actividadesProgreso.Repository.Clientes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;
import java.util.List;
import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;


public interface ClientesRepository extends JpaRepository<ClientesEntity, Long>{
    List<ClientesEntity> findByIdAndStatus(Long id, Boolean status);
    List<ClientesEntity> findByIdEmpresaAndStatus(EmpresasEntity idEmpresa, Boolean status);
}

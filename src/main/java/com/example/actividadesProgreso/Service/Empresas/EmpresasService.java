package com.example.actividadesProgreso.Service.Empresas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.EmpresasInterface;
import com.example.actividadesProgreso.Repository.Empresas.EmpresasRepository;

@Service
public class EmpresasService {

    @Autowired
    EmpresasRepository empresasRepository;

    public List<Object> getBienesUnicos(Long id){
        try {
            return empresasRepository.getBienesUnicos(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<EmpresasInterface> getEmpresasByIdUsuario(Long id){
        try {
            List<EmpresasInterface> query = empresasRepository.getEmpresas(id);
            return query;
        } catch (Exception e) {
            return null;
        }
    }
}

package com.example.actividadesProgreso.Service.Clientes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;
import com.example.actividadesProgreso.Entity.Clientes.DTO.ClientesDTO;
import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;
import com.example.actividadesProgreso.Repository.Clientes.ClientesRepository;
import com.example.actividadesProgreso.Repository.Empresas.EmpresasRepository;

@Service
public class ClientesService {
    
    @Autowired
    ClientesRepository clientesRepository;

    @Autowired
    EmpresasRepository empresasRepository;

    public ResponseEntity<ClientesEntity> addRegister(ClientesEntity var) {
        try {
            clientesRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ClientesDTO> findByIdEmpresaAndStatus(Long idEmpresa){
        try {
            Optional<EmpresasEntity> empresa = empresasRepository.findById(idEmpresa);
            EmpresasEntity empresaId = empresa.get();
            List<ClientesEntity> clientes = clientesRepository.findByIdEmpresaAndStatus(empresaId, true);
            return clientes.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public ClientesDTO entityToDTO(ClientesEntity clientes) {
        return new ClientesDTO(
                clientes);
    }
}

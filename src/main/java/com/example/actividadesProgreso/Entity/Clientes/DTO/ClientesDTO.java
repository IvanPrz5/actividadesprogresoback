package com.example.actividadesProgreso.Entity.Clientes.DTO;

import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {
    private String id;
    private String descripcion;

    public ClientesDTO(ClientesEntity clientes){
        this.id = clientes.getId().toString();
        this.descripcion = clientes.getDescripcion();
    }
}

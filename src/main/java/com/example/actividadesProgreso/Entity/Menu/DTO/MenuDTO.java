package com.example.actividadesProgreso.Entity.Menu.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.actividadesProgreso.Entity.Menu.Interface.MenuAndRoleInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuDTO {
    private Long id;
    private String descripcion;
    private String ruta;
    private String icon;
    private Long orden;

    private List<MenuDTO> menuLista = new ArrayList<>();

    public void convertDto(MenuAndRoleInterface menuInterface){
        this.id = menuInterface.getID();
        this.descripcion = menuInterface.getDescripcion();
        this.ruta = menuInterface.getRuta();
        this.icon = menuInterface.getIcon();
        this.orden = menuInterface.getOrden();
    }

    public void agregarItemLista(MenuDTO menuDto){
        this.menuLista.add(menuDto);
    }
}

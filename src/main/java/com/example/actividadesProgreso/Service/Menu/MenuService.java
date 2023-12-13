package com.example.actividadesProgreso.Service.Menu;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Menu.DTO.MenuDTO;
import com.example.actividadesProgreso.Entity.Menu.Interface.MenuAndRoleInterface;
import com.example.actividadesProgreso.Repository.Menu.MenuRepository;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<MenuDTO> getMenusByIdRol(Long id){
        try {
            List<MenuAndRoleInterface> menus = menuRepository.getMenusByIdRol(id);
            List<MenuDTO> menuDTO = new ArrayList<>();
            for(int i=0; i<menus.size(); i++){
                MenuDTO menuDTO2 = new MenuDTO();
                menuDTO2.convertDto(menus.get(i));
                List<MenuAndRoleInterface> subMenus = menuRepository.getSubMenusByIdRol(id, menuDTO2.getId());
                for(int j=0; j<subMenus.size(); j++){
                    MenuDTO menuD = new MenuDTO();
                    menuD.convertDto(subMenus.get(j));
                    menuDTO2.agregarItemLista(menuD);
                }
                menuDTO.add(menuDTO2);
            }
            return menuDTO;
        } catch (Exception e) {
            return null;
        }
    }
}

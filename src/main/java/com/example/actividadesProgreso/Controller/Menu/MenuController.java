package com.example.actividadesProgreso.Controller.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividadesProgreso.Entity.Menu.DTO.MenuDTO;
import com.example.actividadesProgreso.Service.Menu.MenuService;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/Menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/{id}")
    public List<MenuDTO> getByMenus(@PathVariable("id") Long id){
        return menuService.getMenusByIdRol(id);
    }
}

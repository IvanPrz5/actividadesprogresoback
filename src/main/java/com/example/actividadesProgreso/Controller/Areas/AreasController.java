package com.example.actividadesProgreso.Controller.Areas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividadesProgreso.Entity.Areas.AreasEntity;
import com.example.actividadesProgreso.Service.Areas.AreasService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("admin/Areas")
public class AreasController {
    
    @Autowired
    AreasService areasService;

    @GetMapping("/getAreas")
    public List<AreasEntity> allAreas(){
        return (List<AreasEntity>) areasService.getAll();
    }

    /* 
     * @GetMapping("/getAll/{status}")
    public List<GlobalDTO> allByStatusDTO(@PathVariable("status") Boolean status, Sort sort) {
        return (List<GlobalDTO>) aduanaService.getAll(status, sort);
    }
     */
}

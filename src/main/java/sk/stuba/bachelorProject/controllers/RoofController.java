package sk.stuba.bachelorProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Roof;
import sk.stuba.bachelorProject.services.RoofService;

@RestController
@RequestMapping("roof")
public class RoofController {
    @Autowired
    RoofService roofService;

    @PostMapping("/create")
    private Roof createRoof(Roof roof) {
        return roofService.createRoof(roof);
    }

    @GetMapping("/{id}")
    private Roof getRoofById(@PathVariable(name = "id") String id) {
        return roofService.getRoofById(id);
    }
}

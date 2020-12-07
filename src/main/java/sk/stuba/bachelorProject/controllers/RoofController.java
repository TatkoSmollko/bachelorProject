package sk.stuba.bachelorProject.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Roof;
import sk.stuba.bachelorProject.services.RoofService;

import java.util.List;

@RestController
@RequestMapping("/api/roof")
public class RoofController {
    @Autowired
    RoofService roofService;

    @PostMapping("/createRoof")
    private Roof createRoof(Roof roof) {
        return roofService.createRoof(roof);
    }

    @GetMapping("/getRoofById/{id}")
    private Roof getRoofById(@PathVariable(name = "id") String id) {
        return roofService.getRoofById(id);
    }

    @GetMapping("/getAllRoofs")
    public List<Roof> getAllRoofs() {
        return roofService.getAllRoofs();
    }

    @DeleteMapping("/deleteRoof/{id}")
    public void deleteRoof(@PathVariable(name = "id") String id) {
        roofService.deleteRoof(id);
    }

    @PutMapping("/updateRoof/{id}")
    public Roof updateRoof(@PathVariable(name = "id") String id, @RequestBody Roof roof) {
        return roofService.updateRoof(id, roof);
    }

}

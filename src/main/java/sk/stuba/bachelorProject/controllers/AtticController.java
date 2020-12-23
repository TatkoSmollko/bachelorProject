package sk.stuba.bachelorProject.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Attic;
import sk.stuba.bachelorProject.services.AtticService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attics/")
public class AtticController {
    @Autowired
    AtticService atticService;

    @PostMapping("create")
    private Attic createAttic(@RequestBody Attic attic) {
        return atticService.createAttic(attic);
    }

    @PutMapping("updateAttic/{id}")
    public Attic updateAttic(@PathVariable(name = "id") String id, @RequestBody Attic attic) {
        return atticService.updateAttic(id, attic);
    }

    @GetMapping("getAtticById/{id}")
    public Attic getAtticById(@PathVariable(name = "id") String id) {
        return atticService.getAtticById(id);
    }

    @GetMapping("getAllAtticsByRoofId/{id}")
    public List<Attic> getAllAtticsByRoofId(@PathVariable(name = "id") String roofId) {
        return atticService.getAllAtticsByRoofId(roofId);
    }

    @DeleteMapping("deleteAttic/{id}")
    public ResponseEntity deleteAttic(@PathVariable(name = "id") String atticId) {
        return atticService.deleteAttic(atticId);
    }

}

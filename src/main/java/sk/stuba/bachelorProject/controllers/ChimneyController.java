package sk.stuba.bachelorProject.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Chimney;
import sk.stuba.bachelorProject.services.ChimneyService;

import javax.ws.rs.GET;
import java.util.List;

@RestController
@RequestMapping("/api/chimney/")
public class ChimneyController {
    @Autowired
    ChimneyService chimneyService;

    @PostMapping("createChimney")
    public Chimney createChimney(@RequestBody Chimney chimney) {
        return chimneyService.createChimney(chimney);
    }

    @GetMapping("getChimneyById/{id}")
    public Chimney getChimneyById(@PathVariable(name = "id") String id) {
        return chimneyService.getChimneyById(id);
    }

    @GetMapping("getAllChimneys")
    public List<Chimney> getAllChimneys() {
        return chimneyService.getAllChimneys();
    }

    @DeleteMapping("deleteChimney/{id}")
    public void deleteChimney(@PathVariable(name = "id") String id) {
        chimneyService.deleteChimney(id);
    }

}

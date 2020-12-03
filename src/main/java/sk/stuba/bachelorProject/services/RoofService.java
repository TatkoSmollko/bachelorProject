package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Roof;
import sk.stuba.bachelorProject.repositories.RoofRepository;

@Service
public class RoofService {
    @Autowired
    RoofRepository roofRepository;

    public Roof createRoof(Roof roof) {
        return roofRepository.save(roof);
    }

    public Roof getRoofById(String id) {
        return roofRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Roof"));
    }
}

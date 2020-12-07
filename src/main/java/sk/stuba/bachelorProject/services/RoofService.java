package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Roof;
import sk.stuba.bachelorProject.repositories.RoofRepository;

import java.util.List;

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

    public List<Roof> getAllRoofs(){
        return roofRepository.findAll();
    }

    public void deleteRoof(String id){
        Roof roof = roofRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Roof"));
        roofRepository.delete(roof);
    }

    public Roof updateRoof(String id, Roof roof){
        Roof roofToUpdate = roofRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Roof"));
        roofToUpdate.setAttics(roof.getAttics());
        roofToUpdate.setChimneys(roof.getChimneys());
        roofToUpdate.setItems(roof.getItems());
        return roofRepository.save(roofToUpdate);
    }

}

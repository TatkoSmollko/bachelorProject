package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Attic;
import sk.stuba.bachelorProject.repositories.AtticRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtticService {
    @Autowired
    AtticRepository atticRepository;

    /**
     * @param attic - object which you want to save into the DB
     * @return saved object with ID
     */
    public Attic createAttic(Attic attic) {
        return atticRepository.save(attic);
    }

    /**
     * @param id of attic to update
     * @param attic object of attic which we want to save
     * @return saved object of attic
     */
    public Attic updateAttic(String id, Attic attic) {
        Attic atticToUpdate = atticRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Attic"));
        atticToUpdate.setRoof(attic.getRoof());
        atticToUpdate.setFrontHeight(attic.getFrontHeight());
        atticToUpdate.setLength(attic.getLength());
        atticToUpdate.setRareHeight(attic.getRareHeight());
        atticToUpdate.setWidth(attic.getWidth());
        return atticRepository.save(atticToUpdate);
    }

    /**
     * @param id identifier of requested attic
     * @return attic object which has been requested
     */
    public Attic getAtticById(String id) {
        return atticRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Attic"));
    }

    /**
     * @param roofId id of roof to get all its attics
     * @return list of attics which contain to the roof
     */
    public List<Attic> getAllAtticsByRoofId(String roofId) {
        List<Attic> allAttics = atticRepository.findAll();
        return allAttics.stream().filter(attic -> attic.getRoof().getId().equals(roofId)).collect(Collectors.toList());
    }

    /**
     * @param atticId of deleted attic
     * @return status 200 if everithing was OK
     */
    public ResponseEntity deleteAttic(String atticId) {
        Attic atticToDelete = atticRepository.findById(atticId).orElseThrow(() -> new ObjectNotFoundException(atticId, "Attic"));
        atticRepository.delete(atticToDelete);
        return ResponseEntity.ok().build();
    }


}

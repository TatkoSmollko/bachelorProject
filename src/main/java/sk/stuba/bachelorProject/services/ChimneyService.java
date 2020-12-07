package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Chimney;
import sk.stuba.bachelorProject.repositories.ChimneyRepository;

import java.util.List;

@Service
public class ChimneyService {
    @Autowired
    ChimneyRepository chimneyRepository;

    public Chimney createChimney(Chimney chimney) {
        return chimneyRepository.save(chimney);
    }

    public Chimney getChimneyById(String id) {
        return chimneyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
    }

    public List<Chimney> getAllChimneys() {
        return chimneyRepository.findAll();
    }

    public void deleteChimney(String id) {
        Chimney chimneyToDelete = chimneyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        chimneyRepository.delete(chimneyToDelete);
    }



}

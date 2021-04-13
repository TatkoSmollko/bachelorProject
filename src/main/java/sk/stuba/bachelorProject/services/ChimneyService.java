package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import sk.stuba.bachelorProject.model.Attic;
import sk.stuba.bachelorProject.model.Chimney;
import sk.stuba.bachelorProject.repositories.ChimneyRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Chimney> getAllChimneysByRoofId(String roofId) {
        List<Chimney> allChimneys = chimneyRepository.findAll();
        return allChimneys.stream().filter(chimney -> chimney.getRoof().getId().equals(roofId)).collect(Collectors.toList());
    }


    public void deleteChimney(String id) {
        Chimney chimneyToDelete = chimneyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        chimneyRepository.delete(chimneyToDelete);
    }



}

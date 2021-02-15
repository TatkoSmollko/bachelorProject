package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.PriceOffer;
import sk.stuba.bachelorProject.model.Roof;
import sk.stuba.bachelorProject.model.UsedItem;
import sk.stuba.bachelorProject.repositories.ItemRepository;
import sk.stuba.bachelorProject.repositories.RoofRepository;
import sk.stuba.bachelorProject.repositories.UsedItemRepository;

import java.util.List;

@Service
public class RoofService {
    @Autowired
    RoofRepository roofRepository;

    @Autowired
    PriceOfferService priceOfferService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UsedItemRepository usedItemRepository;

    public Roof createRoof(Roof roof) {
        roof = roofRepository.save(roof);
        UsedItem fatrafol = new UsedItem((int)Math.ceil(priceOfferService.calculateNeededFoil(roof) / 26), itemRepository.findByName("Fatrafol"),priceOfferService.createOffer(new PriceOffer()),roof);
        fatrafol = usedItemRepository.save(fatrafol);
        UsedItem fatrafol2 = usedItemRepository.getOne(fatrafol.getId());
        System.out.println(fatrafol.getParentItem().getName());

        return roof;
    }

    public Roof updateUsedItemsOfRoof(String roofId){
        Roof roof = roofRepository.getOne(roofId);
        return roof;
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

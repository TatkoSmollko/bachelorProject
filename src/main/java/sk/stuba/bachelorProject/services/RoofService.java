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

    public Roof calculateNeededItems(String roofId) {
        Roof roof = roofRepository.findById(roofId).orElseThrow(()-> new ObjectNotFoundException("id", roofId));
        PriceOffer priceOffer =priceOfferService.createOffer(new PriceOffer());
        usedItemRepository.save(new UsedItem((int)Math.ceil(priceOfferService.calculateNeededFoil(roof) / 26), itemRepository.findByName("Fatrafol"),priceOffer,roof));
        usedItemRepository.save(new UsedItem((int)Math.ceil(priceOfferService.getNeededFatrafolRainPlate(roof)),itemRepository.findByName("Okapový plech"),priceOffer,roof));
        usedItemRepository.save(new UsedItem((int)Math.ceil(priceOfferService.calculateNeededScrews(roof)),itemRepository.findByName("Šróby"),priceOffer,roof));
        usedItemRepository.save(new UsedItem((int)Math.ceil(priceOfferService.calculateNeededCornerSlat(roof)),itemRepository.findByName("Rohová lišta"),priceOffer,roof));
        return roof;
    }

    public Roof createEmptyRoof(Roof roof) {
       return roofRepository.save(roof);
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

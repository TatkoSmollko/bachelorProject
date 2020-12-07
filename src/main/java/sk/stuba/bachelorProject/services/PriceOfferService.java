package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.PriceOffer;
import sk.stuba.bachelorProject.repositories.PriceOfferRepository;

import java.util.List;

@Service
public class PriceOfferService {
    @Autowired
    PriceOfferRepository priceOfferRepository;

    public PriceOffer createPriceOffer(PriceOffer priceOffer) {
        return priceOfferRepository.save(priceOffer);
    }

    public PriceOffer getPriceOfferById(String id) {
        return priceOfferRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
    }

    public List<PriceOffer> getAllPriceOffers() {
        return priceOfferRepository.findAll();
    }

    public void deletePriceOffer(String id) {
        PriceOffer priceOffer = priceOfferRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        priceOfferRepository.delete(priceOffer);
    }

    public PriceOffer updatePriceOffer(String id, PriceOffer priceOffer){
        PriceOffer priceOfferToUpdate = priceOfferRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        priceOfferToUpdate.setItems(priceOffer.getItems());
        priceOfferToUpdate.setCustomerName(priceOffer.getCustomerName());
        priceOfferToUpdate.setStatus(priceOffer.getStatus());
        return priceOfferRepository.save(priceOfferToUpdate);
    }
}

package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Attic;
import sk.stuba.bachelorProject.model.Chimney;
import sk.stuba.bachelorProject.model.PriceOffer;
import sk.stuba.bachelorProject.model.Roof;
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

    public PriceOffer updatePriceOffer(String id, PriceOffer priceOffer) {
        PriceOffer priceOfferToUpdate = priceOfferRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        priceOfferToUpdate.setItems(priceOffer.getItems());
        priceOfferToUpdate.setCustomerName(priceOffer.getCustomerName());
        priceOfferToUpdate.setStatus(priceOffer.getStatus());
        return priceOfferRepository.save(priceOfferToUpdate);
    }

    /**
     * @param roof for calculating area which we need to cover with fatrafol
     * @return area value in squareMeters
     */
    public double calculateNeededFoil(Roof roof) {
        double downArea = roof.getHeigth() * roof.getLength();
        downArea += calculateAreaOfChimneys(roof.getChimneys());
        downArea += calculateAreaOfAttics(roof.getAttics());
        return calculateAreaWithAddedPercents(downArea);
    }

    /**
     * @param chimneys of actually using roof
     * @return value of needed foil in square meters
     */
    public double calculateAreaOfChimneys(List<Chimney> chimneys) {
        double area = 0;
        for (Chimney chimney : chimneys) {
            area += 2 * (chimney.getHeigth() * 0.3);
            area += 2 * (chimney.getWidth() * 0.3);
        }
        return area;
    }

    /**
     * @param attics to calculate area of all of them
     * @return value of needed foil in square meters by using pattern S = (a+c)*d/2 where a = first height, c = second height and d= length
     * of attic
     */
    public double calculateAreaOfAttics(List<Attic> attics) {
        double area = 0;
        for (Attic attic : attics) {
            area += (attic.getFrontHeight()+attic.getRareHeight())*attic.getLength()/2;
            area += attic.getLength()*attic.getWidth();
        }
        return area;
    }
    public double calculateAreaWithAddedPercents(double area){
        return area/100*115;
    }
}

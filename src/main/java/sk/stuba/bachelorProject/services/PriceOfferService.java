package sk.stuba.bachelorProject.services;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import sk.stuba.bachelorProject.enums.PriceOfferStatus;
import sk.stuba.bachelorProject.model.*;
import sk.stuba.bachelorProject.repositories.PriceOfferRepository;
import sk.stuba.bachelorProject.repositories.RoofRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceOfferService {
    @Autowired
    PriceOfferRepository priceOfferRepository;

    @Autowired
    RoofRepository roofRepository;

    @Autowired
    XlsService xlsService;

    public PriceOffer createOffer(PriceOffer priceOffer) {
        priceOffer.setCustomerName(priceOffer.getCustomerName());
        priceOffer.setStatus(PriceOfferStatus.NEW);
        priceOffer.setItems(new ArrayList<>());
        return priceOfferRepository.save(priceOffer);
    }

    public void finishPriceOffer(String name, String priceOfferId) throws IOException, InvalidFormatException {
        PriceOffer priceOffer = priceOfferRepository.getOne(priceOfferId);
        priceOffer.setCustomerName(name);
        double price = 0;
        for(UsedItem item : priceOffer.getItems()){
            price += (item.getCount()*item.getParentItem().getPrice());
        }
        priceOffer.setPrice(price);
        priceOfferRepository.save(priceOffer);
        xlsService.createPriceOfferExcel(name, priceOfferId);
    }

    public PriceOffer createPriceOffer(PriceOffer priceOffer, String roofId) {
        Roof roof = roofRepository.findById(roofId).orElseThrow(() -> new ObjectNotFoundException("id", roofId));
        priceOffer.setCustomerName(priceOffer.getCustomerName());
        for (UsedItem item : roof.getItems()) {
            priceOffer.getItems().add(item);
        }
        return priceOfferRepository.save(priceOffer);
    }

    public void updateStatusPriceOffer(@PathVariable String id, PriceOffer priceOffer) {
        if (priceOffer.getStatus() == PriceOfferStatus.ACCEPTED) {
            //musim prejst vsetky polozky v sklade a dostupne vymazat
            //ak je poloziek menej, odpocitam to, co je na sklade + potrebujem dat oznam, kolko treba dokupit
            //zmenim stav cenovej ponuky na akceptovana
            for (UsedItem usedItem : priceOffer.getItems()) {

            }


        } else if (priceOffer.getStatus() == PriceOfferStatus.DECLINED) {

        }

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
        roof.getItems().add(new UsedItem());
        roofRepository.save(roof);
        return calculateAreaWithAddedPercents(downArea);
    }

    /**
     * @param roof
     * @return counted srews . There must be 6 screws in one square meter.
     */
    public double calculateNeededScrews(Roof roof) {
        return roof.getHeigth() * roof.getLength() * 6;
    }

    /**
     * @param roof
     * @return count of needed cornerSlats.
     */
    public double calculateNeededCornerSlat(Roof roof) {
        Double size = 0.0;
        for (Attic attic : roof.getAttics()) {
            size += attic.getLength();
        }

        for(Chimney chimney : roof.getChimneys()){
            size +=( chimney.getWidth()*2 + chimney.getHeigth()*2);
        }
        return size / 2.5;
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
            area += (attic.getFrontHeight() + attic.getRareHeight()) * attic.getLength() / 2;
            area += attic.getLength() * attic.getWidth();
        }
        return area;
    }

    public double calculateAreaWithAddedPercents(double area) {
        return area / 100 * 115;
    }

    /**
     * @param roof for calculating needed rails
     * @return number of needed rails
     */
    public double calculateNeededRail(Roof roof) {
        double rails = 0;
        rails += ((roof.getHeigth() * 2) + (roof.getLength() * 2)) / 2;
        for (Chimney chimney : roof.getChimneys()) {
            rails += ((chimney.getHeigth() * 2) + (chimney.getWidth() * 2)) / 2;
        }
        return rails;
    }

    public double calculateNeededFatrafolPlate(Roof roof) {
        double plates = 0;
        for (Attic attic : roof.getAttics()) {
            plates += attic.getLength() / 2.5;
        }
        return plates;
    }

    /**
     * @param roof for calculating of needed rainPlates
     * @return number of needed meters of rainPlates. It is calculating using sides of roof without attic.
     */
    //TODO zamysliet sa nad moznou chybovostou, pripadne nad vyuzitim pre okap
    public double getNeededFatrafolRainPlate(Roof roof) {
        double size = roof.getHeigth() * 2 + roof.getLength() * 2;
        for (Attic attic : roof.getAttics()) {
            size -= attic.getLength();
        }
        if (size > 0) {
            return size / 2.5;
        } else {
            return 0;
        }
    }
}

package sk.stuba.bachelorProject.controllers;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.PriceOffer;
import sk.stuba.bachelorProject.model.Roof;
import sk.stuba.bachelorProject.services.PriceOfferService;
import sk.stuba.bachelorProject.services.RoofService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/priceOffer/")
public class PriceOfferController {
    @Autowired
    PriceOfferService priceOfferService;

    @Autowired
    RoofService roofService;

    @PostMapping("createPriceOffer")
    public PriceOffer createPriceOffer(@RequestBody PriceOffer priceOffer) {
        return priceOfferService.createPriceOffer(priceOffer,"87638b14-b5d8-4b11-9eb6-e9ef8ebe2727");
    }

    @GetMapping("finishPriceOffer/{priceOfferId}")
    public void finishPriceOffer(@PathVariable(name = "priceOfferId") String priceOfferId) throws IOException, InvalidFormatException {
        priceOfferService.finishPriceOffer("Tomas",priceOfferId);
    }

    @GetMapping("getPriceOfferById/{id}")
    public PriceOffer getPriceOfferById(@PathVariable(name = "id") String id) {
        return priceOfferService.getPriceOfferById(id);
    }

    @GetMapping("getAllPriceOffers")
    public List<PriceOffer> getAllPriceOffers() {
        return priceOfferService.getAllPriceOffers();
    }

    @DeleteMapping("deletePriceOffer/{id}")
    public void deletePriceOffer(@PathVariable(name = "id") String id) {
    }
    @PutMapping("updatePriceOffer/{id}")
    public PriceOffer updatePriceOffer(@PathVariable(name = "id") String id,@RequestBody PriceOffer priceOffer) {
        return priceOfferService.updatePriceOffer(id,priceOffer);
    }
    @GetMapping("getValue/{id}")
    public double getValue(@PathVariable(name = "id")String id){
        Roof roof = roofService.getRoofById(id);
        return priceOfferService.calculateNeededFoil(roof);
    }
}

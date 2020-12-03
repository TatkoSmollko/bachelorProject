package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.PriceOffer;

@Repository
public interface PriceOfferRepository extends JpaRepository<PriceOffer,String> {
}

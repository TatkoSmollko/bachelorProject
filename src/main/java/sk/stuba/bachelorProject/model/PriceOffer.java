package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;
import sk.stuba.bachelorProject.enums.PriceOfferStatus;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;


@Entity
@Table(name="priceOffers")
public class PriceOffer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
    private String id;

    @Column
    private PriceOfferStatus status;

    @OneToMany(mappedBy="priceOffer")
    private List<UsedItem> items;

}

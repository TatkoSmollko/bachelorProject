package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;
import sk.stuba.bachelorProject.enums.PriceOfferStatus;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "priceOffers")
public class PriceOffer {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @Column
    private String customerName;
    @Column
    private PriceOfferStatus status;
    @OneToMany(mappedBy = "priceOffer")
    private List<UsedItem> items;

    public PriceOffer() {
        super();
    }

    public PriceOffer(String customerName, PriceOfferStatus status, List<UsedItem> items) {
        super();
        this.customerName = customerName;
        this.status = status;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceOffer)) return false;
        PriceOffer that = (PriceOffer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerName, that.customerName) &&
                status == that.status &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, status, items);
    }
}

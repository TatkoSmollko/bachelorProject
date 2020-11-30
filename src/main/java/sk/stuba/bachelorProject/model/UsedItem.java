package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "usedItems")
public class UsedItem {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
    private String id;

    @Column
    Integer count;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item parentItem;

    @ManyToOne
    @JoinColumn(name="price_offerId", nullable=false)
    private PriceOffer priceOffer;

    @ManyToOne
    @JoinColumn(name = "roof_id", nullable = false)
    private Roof roof;


    public UsedItem() {
    }
}

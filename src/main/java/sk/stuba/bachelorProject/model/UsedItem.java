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
    private Integer count;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item parentItem;

    @ManyToOne
    @JoinColumn(name="price_offerId", nullable=false)
    private PriceOffer priceOffer;

    @ManyToOne
    @JoinColumn(name = "roof_id", nullable = false)
    private Roof roof;

    public UsedItem(Integer count, Item parentItem, PriceOffer priceOffer, Roof roof) {
        this.count = count;
        this.parentItem = parentItem;
        this.priceOffer = priceOffer;
        this.roof = roof;
    }

    public UsedItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Item getParentItem() {
        return parentItem;
    }

    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }

    public PriceOffer getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(PriceOffer priceOffer) {
        this.priceOffer = priceOffer;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }
}

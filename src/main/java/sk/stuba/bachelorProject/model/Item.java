package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @ManyToMany
    @JoinTable(
            name = "store_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<Store> stores;
    @Column
    private Integer count;
    @Column
    private Double price;
    @Column
    private Double size;

    public Item() {
        super();
    }

    public Item(List<Store> stores, Integer count, Double price, Double size) {
        super();
        this.stores = stores;
        this.count = count;
        this.price = price;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(stores, item.stores) &&
                Objects.equals(count, item.count) &&
                Objects.equals(price, item.price) &&
                Objects.equals(size, item.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stores, count, price, size);
    }
}

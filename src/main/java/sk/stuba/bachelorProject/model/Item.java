package sk.stuba.bachelorProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items")
@JsonIgnoreProperties(value = {"store"}, allowSetters = true)
public class Item {

    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column
    private Integer count;
    @Column
    private Double price;
    @Column
    private Double size;
    @Column
    private String name;

    public Item() {
        super();
    }

    public Item(Store stores, Integer count, Double price, Double size, String name) {
        super();
        this.store = stores;
        this.count = count;
        this.price = price;
        this.size = size;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store stores) {
        this.store = stores;
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
                Objects.equals(store, item.store) &&
                Objects.equals(count, item.count) &&
                Objects.equals(price, item.price) &&
                Objects.equals(size, item.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, count, price, size);
    }
}

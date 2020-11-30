package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @ManyToMany(mappedBy = "stores")
    private List<Item> items;

    public Store() {
        super();
    }

    public Store(List<Item> items, String id) {
        super();
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return Objects.equals(getId(), store.getId()) &&
                Objects.equals(getItems(), store.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItems());
    }
}

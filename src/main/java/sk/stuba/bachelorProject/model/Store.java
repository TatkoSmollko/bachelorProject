package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name = "stores")
@Entity
public class Store {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @OneToMany(mappedBy = "store")
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

    public void setId(String id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

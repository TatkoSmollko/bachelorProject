package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stores")
public class Store {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
    private String id;

    @ManyToMany(mappedBy="stores")
    private List<Item> items;

    public Store() {
    }

    public Store(List<Item> items) {
        super();
        this.items=items;
    }
}

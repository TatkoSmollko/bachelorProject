package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="items")
public class Item {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
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


}

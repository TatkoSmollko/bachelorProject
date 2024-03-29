package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "roofs")
public class Roof {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @Column(name = "height")
    private double heigth;
    @Column(name = "length")
    private double length;
    @Column
    private String priceOfferId;
    @OneToMany(mappedBy = "roof")
    private List<Attic> attics;
    @OneToMany(mappedBy = "roof")
    private List<Chimney> chimneys;
    @OneToMany(mappedBy = "roof")
    private List<UsedItem> items;

    public Roof() {
        super();
    }

    public Roof(double heigth, double length, List<Attic> attics, List<Chimney> chimneys, List<UsedItem> items,String roofId) {
        this.heigth = heigth;
        this.length = length;
        this.attics = attics;
        this.chimneys = chimneys;
        this.items = items;
        this.priceOfferId = roofId;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public double getLength() {
        return length;
    }


    public void setLength(double length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public String getPriceOfferId() {
        return priceOfferId;
    }

    public void setPriceOfferId(String priceOfferId) {
        this.priceOfferId = priceOfferId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Attic> getAttics() {
        return attics;
    }

    public void setAttics(List<Attic> attics) {
        this.attics = attics;
    }

    public List<Chimney> getChimneys() {
        return chimneys;
    }

    public void setChimneys(List<Chimney> chimneys) {
        this.chimneys = chimneys;
    }


    public List<UsedItem> getItems() {
        return items;
    }

    public void setItems(List<UsedItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roof)) return false;
        Roof roof = (Roof) o;
        return Objects.equals(getId(), roof.getId()) &&
                Objects.equals(getAttics(), roof.getAttics()) &&
                Objects.equals(getChimneys(), roof.getChimneys()) &&
                Objects.equals(getItems(), roof.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAttics(), getChimneys(), getItems());
    }
}

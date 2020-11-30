package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attics")
public class Attic {

    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;

    @ManyToOne
    @JoinColumn(name = "roof_id", nullable = false)
    private Roof roof;

    @Column
    private Double frontHeight;

    @Column
    private Double rareHeight;

    @Column
    private Double width;

    @Column
    private Double length;

    public Attic() {
    }


    public Attic(Roof roof, Double frontHeight, Double rareHeight, Double width, Double length) {
        super();
        this.roof = roof;
        this.frontHeight = frontHeight;
        this.rareHeight = rareHeight;
        this.width = width;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public Double getFrontHeight() {
        return frontHeight;
    }

    public void setFrontHeight(Double frontHeight) {
        this.frontHeight = frontHeight;
    }

    public Double getRareHeight() {
        return rareHeight;
    }

    public void setRareHeight(Double rareHeight) {
        this.rareHeight = rareHeight;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attic)) return false;
        Attic attic = (Attic) o;
        return getId().equals(attic.getId()) &&
                Objects.equals(getRoof(), attic.getRoof()) &&
                Objects.equals(getFrontHeight(), attic.getFrontHeight()) &&
                Objects.equals(getRareHeight(), attic.getRareHeight()) &&
                Objects.equals(getWidth(), attic.getWidth()) &&
                Objects.equals(getLength(), attic.getLength());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoof(), getFrontHeight(), getRareHeight(), getWidth(), getLength());
    }
}

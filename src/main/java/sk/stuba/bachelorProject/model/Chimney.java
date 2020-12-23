package sk.stuba.bachelorProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chimneys")
@JsonIgnoreProperties(value = {"roof"}, allowSetters = true)
public class Chimney {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String Id;

    @Column
    private Double width;

    @Column
    private Double heigth;

    @ManyToOne
    @JoinColumn(name = "roof_id", nullable = false)
    private Roof roof;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeigth() {
        return heigth;
    }

    public void setHeigth(Double heigth) {
        this.heigth = heigth;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chimney)) return false;
        Chimney chimney = (Chimney) o;
        return getId().equals(chimney.getId()) &&
                Objects.equals(getWidth(), chimney.getWidth()) &&
                Objects.equals(getHeigth(), chimney.getHeigth()) &&
                Objects.equals(getRoof(), chimney.getRoof());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWidth(), getHeigth(), getRoof());
    }
}

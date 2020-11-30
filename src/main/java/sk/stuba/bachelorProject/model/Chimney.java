package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chimneys")
public class Chimney {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String Id;

    @Column
    private Long width;

    @Column
    private Long heigth;

    @ManyToOne
    @JoinColumn(name = "roof_id", nullable = false)
    private Roof roof;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeigth() {
        return heigth;
    }

    public void setHeigth(Long heigth) {
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

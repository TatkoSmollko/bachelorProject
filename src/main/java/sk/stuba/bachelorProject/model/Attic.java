package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="attics")
public class Attic {



    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
    private String id;

    @ManyToOne
    @JoinColumn(name="roof_id", nullable=false)
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





}

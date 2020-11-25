package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="chimneys")
public class Chimney {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
    private String Id;

    @ManyToOne
    @JoinColumn(name="roof_id", nullable=false)
    private Roof roof;
}

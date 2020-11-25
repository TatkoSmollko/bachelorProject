package sk.stuba.bachelorProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roofs")
public class Roof {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name="id",length = 40)
    private String id;

    @OneToMany(mappedBy="roof")
    private List<Attic> attics;

    @OneToMany(mappedBy="roof")
    private List<Chimney> chimneys;
}

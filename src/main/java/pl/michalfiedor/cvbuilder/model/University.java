package pl.michalfiedor.cvbuilder.model;

import javax.persistence.*;

@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}

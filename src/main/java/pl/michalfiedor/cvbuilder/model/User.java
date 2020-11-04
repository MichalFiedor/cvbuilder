package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    @OneToOne
    private Cv cv;
}

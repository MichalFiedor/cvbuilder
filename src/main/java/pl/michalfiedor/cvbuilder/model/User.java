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
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    @OneToOne
    private City city;
    @OneToOne
    private Cv cv;
}

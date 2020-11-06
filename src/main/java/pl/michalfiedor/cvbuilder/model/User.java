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
    private String userName;
    private String password;
    private String userEmail;
    private String userPhoneNumber;
    private String userCity;
    @OneToOne
    private Cv cv;
}

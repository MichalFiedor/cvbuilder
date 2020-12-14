package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email(message = "Enter correct email address.")
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Cv> cvs;
    @ManyToMany
    @JoinTable(name = "user_roles")
    private Collection<Role> roles = new ArrayList<>();

    public void addCv(Cv cv){
        cvs.add(cv);
    }

    public void addRole(Role role){
        roles.add(role);
    }
}

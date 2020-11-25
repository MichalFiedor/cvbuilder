package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2, message = "Login must be greater than 2 characters.")
    @NotBlank(message = "Login must be without space.")
    private String login;
    private String password;
    @Email(message = "Enter correct email address.")
    @Column(unique = true)
    private String email;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Cv> cvs;

    public void addCv(Cv cv){
        cvs.add(cv);
    }
}

package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Enter correct company name.")
    private String companyName;
    @NotBlank(message = "Enter correct position name.")
    private String position;
    @NotBlank(message = "You must enter start date.")
    private String start;
    private String end;
    @Column(length = 150)
    @Size(min = 5, message = "Text must have more than 5 characters.")
    @Size(max = 150, message = "Text must have no more than 150 characters")
    private String description;
}

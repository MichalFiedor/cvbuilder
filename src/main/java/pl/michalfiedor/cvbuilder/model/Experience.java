package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Incorrect value.")
    private String companyName;
    @NotBlank(message = "Incorrect value")
    private String position;
    @NotNull(message = "You must enter start date.")
    private String start;
    private String end;
    @Column(length = 150)
    @Range(min = 5, max = 150, message = "Text must have more than \" +\n" +
            "            \"5, and not more than 150 characters")
    private String description;
}

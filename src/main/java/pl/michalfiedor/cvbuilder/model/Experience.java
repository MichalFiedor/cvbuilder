package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import pl.michalfiedor.cvbuilder.validator.annotation.IsAfterStartDateForExperience;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Getter
@Setter
@IsAfterStartDateForExperience
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Enter correct company name.")
    private String companyName;
    @NotBlank(message = "Enter correct position name.")
    @Pattern(regexp = "^[aA-zZ\" \"]{2,}$",
    message = "Position name can not contain digits.")
    private String position;
    @NotBlank(message = "You must enter start date.")
    private String start;
    private String end;
    @Column(length = 150)
    @Size(min = 5, message = "Text must have more than 5 characters.")
    @Size(max = 150, message = "Text must have no more than 150 characters")
    private String description;
}

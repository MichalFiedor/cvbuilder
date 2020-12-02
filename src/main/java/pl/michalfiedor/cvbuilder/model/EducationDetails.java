package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import pl.michalfiedor.cvbuilder.validationGroup.EducationDetailValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class EducationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(groups = EducationDetailValidationGroup.class,
            message = "You must enter start date.")
    private String start;
    private String end;
    @Column(length = 160)
    @Size(groups = EducationDetailValidationGroup.class,
            min = 3, message = "Text must have more than 3 characters.")
    @Size(groups = EducationDetailValidationGroup.class,
            max = 150, message = "Text must have no more than 150 characters.")
    private String degree;
    @OneToOne
    @JoinColumn(name = "university_id")
    @NotNull(groups = EducationDetailValidationGroup.class,
            message = "Choose your university.")
    private University university;

}

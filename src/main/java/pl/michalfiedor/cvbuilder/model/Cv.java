package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 450)
//    @Range(min = 5, max = 450, message = "Text must have more than " +
//            "5 and not more than 450 characters.")
    @Min(5)
    private String aboutMe;
    @NotBlank(message = "Incorrect value.")
    @Size(min = 2, message = "Too short name")
    private String firstName;
    @Size(min = 2, message = "To short last name.")
    @NotBlank(message = "Incorrect value.")
    private String lastName;
    @Email(message = "Enter correct email address.")
    private String email;
    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{3}", message = "Incorrect phone number.")
    private String phoneNumber;
    @OneToOne
    private City city;
    @OneToMany
    @JoinColumn(name = "cv_id")
    private List<Experience> experiences;
    @OneToMany
    @JoinColumn(name = "cv_id")
    private List<EducationDetails> educationDetailsList;
    @ManyToMany
    @JoinTable(name = "itToolId_CvId")
    private List<ItTool> itTools;
    @ManyToMany
    @JoinTable(name = "languageId_CvId")
    private List<Language> languages;
    private String imagePath;
    private String cvPath;

    public void addExperienceToCollection(Experience experience){
        experiences.add(experience);
    }

    public void addEducationDetailToCollection(EducationDetails ud){
        educationDetailsList.add(ud);
    }
}

package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //validator max 400 characters
    @Column(length = 450)
    private String aboutMe;
    private String firstName;
    private String lastName;
    private String email;
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

    public void addExperienceToCollection(Experience experience){
        experiences.add(experience);
    }

    public void addEducationDetailToCollection(EducationDetails ud){
        educationDetailsList.add(ud);
    }
}

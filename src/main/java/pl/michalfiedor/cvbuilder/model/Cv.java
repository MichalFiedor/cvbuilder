package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    @ManyToMany
    @JoinTable(name = "univeristyId_CvId")
    private List<University> universities;
    @ManyToMany
    @JoinTable(name = "itToolId_CvId")
    private List<itTool> itTools;
    @ManyToMany
    @JoinTable(name = "languageId_CvId")
    private List<Language> languages;

    public void addExperienceToCollection(Experience experience){
        experiences.add(experience);
    }
}

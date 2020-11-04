package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String aboutMe;
    @OneToMany
    @JoinColumn(name = "experience_id")
    private List<Experience> experiences;
    @ManyToMany
    @JoinTable(name = "educationId_CvId")
    private List<Education> educations;
    @ManyToMany
    @JoinTable(name = "itToolId_CvId")
    private List<itTool> itTools;
    @ManyToMany
    @JoinTable(name = "languageId_CvId")
    private List<Language> languages;
}

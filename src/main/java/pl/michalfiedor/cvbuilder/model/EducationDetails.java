package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class EducationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String start;
    private String end;
    @Column(length = 160)
    private String degree;
    @OneToOne
    @JoinColumn(name = "university_id")
    private University university;

}

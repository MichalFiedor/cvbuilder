package pl.michalfiedor.cvbuilder.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String companyName;
    private String position;
    private String start;
    private String end;
    private String description;
}

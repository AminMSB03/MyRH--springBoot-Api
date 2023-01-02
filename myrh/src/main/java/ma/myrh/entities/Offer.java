package ma.myrh.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity @Data
public class Offer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String  profileRequired;
    private String ville;
    private String educationLevel;
    private String salary;

    @ManyToOne
    private Company company;


}

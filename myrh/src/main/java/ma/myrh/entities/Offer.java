package ma.myrh.entities;


import jakarta.persistence.*;
import lombok.*;
import ma.myrh.enums.OfferStatus;

import java.time.LocalDate;


@AllArgsConstructor
@Data @NoArgsConstructor
@Entity public class Offer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAT;
    private String  profileRequired;
    private String ville;
    private String educationLevel;
    private String salary;
    @Column(columnDefinition = "int default '0'")
    private OfferStatus status;

    @ManyToOne
    private Company company;


}

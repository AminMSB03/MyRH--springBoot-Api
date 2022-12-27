package ma.myrh.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor
@Entity public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifier;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    private String image;

    @OneToMany(mappedBy = "company")
    private List<Offer> offers;

}

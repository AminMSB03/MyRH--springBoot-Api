package ma.myrh.dtos;


import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfferDtoRequest {
    private String title;
    private String description;
    private String  profileRequired;
    private String ville;
    private String educationLevel;
    private String salary;
}

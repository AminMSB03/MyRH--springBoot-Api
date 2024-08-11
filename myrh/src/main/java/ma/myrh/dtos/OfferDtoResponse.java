package ma.myrh.dtos;

import lombok.*;


import java.time.LocalDate;

@Data
public class OfferDtoResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAT;
    private String  profileRequired;
    private String ville;
    private String educationLevel;
    private String salary;
}

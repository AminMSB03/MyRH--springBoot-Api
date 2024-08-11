package ma.myrh.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferDtoResponseTwo {

        private Long id;
        private String title;
        private String description;
        private LocalDate createdAT;
        private String  profileRequired;
        private String ville;
        private String educationLevel;
        private String salary;
        private CompanyDtoRequest company;

}

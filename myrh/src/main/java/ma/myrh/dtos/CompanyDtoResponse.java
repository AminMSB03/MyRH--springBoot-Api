package ma.myrh.dtos;

import lombok.*;
;import java.util.List;

@Data
public class CompanyDtoResponse {

    private Long id;
    private String identifier;
    private String name;
    private String email;
    private String phoneNumber;
    private String image;
    private List<OfferDtoResponse> offers;


}

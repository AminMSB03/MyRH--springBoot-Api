package ma.myrh.dtos;

import lombok.Builder;
import lombok.Data;
import ma.myrh.entities.Offer;

import java.util.List;
@Data @Builder
public class CompanyDto {

    private Long id;
    private String identifier;
    private String name;
    private String email;
    private String phoneNumber;
    private String image;

    private List<Offer> offers;

}

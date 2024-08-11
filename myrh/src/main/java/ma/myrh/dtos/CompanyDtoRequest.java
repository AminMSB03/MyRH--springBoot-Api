package ma.myrh.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CompanyDtoRequest {

    private String name;
    private String email;
    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String image;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile file;


}

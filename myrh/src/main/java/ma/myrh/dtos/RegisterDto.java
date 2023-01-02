package ma.myrh.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder @Data
public class RegisterDto {
    CompanyDto companyDto;
    MultipartFile file;
}

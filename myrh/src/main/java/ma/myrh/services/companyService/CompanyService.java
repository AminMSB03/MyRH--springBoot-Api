package ma.myrh.services.companyService;

import ma.myrh.dtos.CompanyDtoRequest;
import ma.myrh.dtos.CompanyDtoResponse;
import ma.myrh.entities.Company;
import org.mapstruct.Mapper;

import java.io.IOException;


public interface CompanyService {

    Company login(String email);
    CompanyDtoResponse register(CompanyDtoRequest companyDtoRequest) throws IOException;
    CompanyDtoResponse getCompanyByEmail(String email);

}

package ma.myrh.services.companyService;

import ma.myrh.entities.Company;
import org.mapstruct.Mapper;


public interface CompanyService {

    Company login(String email);

    Company register(Company company);


}

package ma.myrh.services.companyService;

import ma.myrh.repositories.CompanyRepository;
import ma.myrh.entities.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service public class CompanyServiceImpl implements CompanyService{

    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company login(String email) {
        Optional<Company> companyOptional = this.companyRepository.findByEmail(email);
        if(companyOptional.isPresent()){
            return companyOptional.get();
        }
        return null;
    }
}

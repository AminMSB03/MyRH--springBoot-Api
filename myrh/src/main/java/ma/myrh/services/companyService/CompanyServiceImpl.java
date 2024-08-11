package ma.myrh.services.companyService;

import ma.myrh.dtos.CompanyDtoRequest;
import ma.myrh.dtos.CompanyDtoResponse;
import ma.myrh.dtos.OfferDtoResponse;
import ma.myrh.entities.Offer;
import ma.myrh.mapper.CompanyMapper;
import ma.myrh.mapper.OfferMapper;
import ma.myrh.repositories.CompanyRepository;
import ma.myrh.entities.Company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
@Service public class CompanyServiceImpl implements CompanyService{

    CompanyRepository companyRepository;
    OfferMapper offerMapper;
    CompanyMapper companyMapper;
    PasswordEncoder passwordEncoder;

    private final String path = "C:\\Users\\Youcode\\Desktop\\briefs2\\MyRH--springBoot-Api\\myrhFront\\src\\assets\\";

    public CompanyServiceImpl(CompanyRepository companyRepository, OfferMapper offerMapper, CompanyMapper companyMapper, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.offerMapper = offerMapper;
        this.companyMapper = companyMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Company login(String email) {
        Optional<Company> companyOptional = this.companyRepository.findByEmail(email);
        if(companyOptional.isPresent()) return companyOptional.get();
        return null;
    }

    @Override
    public CompanyDtoResponse register(CompanyDtoRequest companyDtoRequest) throws IOException {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong());
        String randomNumberStr = String.format("%024d", randomNumber);

        Company company = this.companyMapper.companyDto(companyDtoRequest);
        company.setPassword(this.passwordEncoder.encode(company.getPassword()));
        this.companyRepository.save(company);

        String filePath = this.path+companyDtoRequest.getFile().getOriginalFilename();
        companyDtoRequest.getFile().transferTo(new File(filePath));
        CompanyDtoResponse companyDtoResponse =this.companyMapper.companyToCompanyDtoResponse(company);
        return companyDtoResponse;
    }

    @Override
    public CompanyDtoResponse getCompanyByEmail(String email) {
        Optional<Company> companyOptional  =  this.companyRepository.findByEmail(email);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            //System.out.println(company.getOffers()+"-".repeat(100));
            List<OfferDtoResponse> offerDtoResponses = this.offerMapper.listOfferDtoListOfferR(company.getOffers());
            CompanyDtoResponse companyDtoResponse = this.companyMapper.companyToCompanyDtoResponse(company);
            companyDtoResponse.setOffers(offerDtoResponses);

            return companyDtoResponse;
        }
        return null;
    }
}

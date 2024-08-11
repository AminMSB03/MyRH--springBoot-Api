package ma.myrh.services.offerService;

import ma.myrh.dtos.*;
import ma.myrh.entities.Company;
import ma.myrh.entities.Offer;
import ma.myrh.enums.OfferStatus;
import ma.myrh.mapper.CompanyMapper;
import ma.myrh.mapper.OfferMapper;
import ma.myrh.repositories.OfferRepository;
import ma.myrh.services.companyService.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service public class OfferServiceImpl implements OfferService {

    CompanyService companyService;
    OfferMapper offerMapper;
    CompanyMapper companyMapper;
    OfferRepository offerRepository;

    public OfferServiceImpl(CompanyService companyService, OfferMapper offerMapper, CompanyMapper companyMapper, OfferRepository offerRepository) {
        this.companyService = companyService;
        this.offerMapper = offerMapper;
        this.companyMapper = companyMapper;
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDtoResponseTwo addOffer(OfferDtoRequest offerDtoRequest,String email) {

        Offer offer = this.offerMapper.offerDtoRequestToOffer(offerDtoRequest);
        Company company =  this.companyService.login(email);
        offer.setCompany(company);
        offer.setCreatedAT(LocalDate.now());
        offer.setStatus(OfferStatus.PENDING);
        this.offerRepository.save(offer);

        OfferDtoResponseTwo offerDtoResponseTwo = this.offerMapper.offerToOfferDtoResponseTwo(offer);
        CompanyDtoRequest companyDtoRequest = this.companyMapper.companyToCompanyRequest(company);
        System.out.println(companyDtoRequest);
        return offerDtoResponseTwo;
    }

    @Override
    public List<OfferDtoResponse> getPendingOffers() {
        return null;
    }

    @Override
    public List<OfferDtoResponseTwo> getOfferByStatus(OfferStatus status) {
        List<Offer> offerByStatusIs = this.offerRepository.findOfferByStatusIs(status);

        List<OfferDtoResponseTwo> offerDtoResponseTwos = this.offerMapper.ListOfferToListOfferDtoResponseTwo(offerByStatusIs);
        return offerDtoResponseTwos;
    }

    @Override
    public List<OfferDtoResponseTwo> getOwnOffers(String email) {
        Company company =  this.companyService.login(email);
        List<Offer> offerByCompany = this.offerRepository.findOfferByCompany(company);

        List<OfferDtoResponseTwo> offerDtoResponseTwos = this.offerMapper.ListOfferToListOfferDtoResponseTwo(offerByCompany);
        return offerDtoResponseTwos;
    }

    @Override
    public Boolean updateOffer(String action, Long id) {

        Optional<Offer> optionalOffer = this.offerRepository.findById(id);
        if(optionalOffer.isPresent()){
            Offer offer = optionalOffer.get();
            if(action.equals("refuse"))
                offer.setStatus(OfferStatus.REFUSED);
            else if(action.equals("accept"))
                offer.setStatus(OfferStatus.ACCEPTED);
            this.offerRepository.save(offer);
            return true;
        }
        return false;
    }

}

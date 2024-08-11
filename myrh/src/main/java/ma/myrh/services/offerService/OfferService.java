package ma.myrh.services.offerService;

import ma.myrh.dtos.OfferDtoRequest;
import ma.myrh.dtos.OfferDtoResponse;
import ma.myrh.dtos.OfferDtoResponseTwo;
import ma.myrh.enums.OfferStatus;

import java.util.List;

public interface OfferService {

    public OfferDtoResponseTwo addOffer(OfferDtoRequest offerDtoRequest,String email);
    public List<OfferDtoResponse> getPendingOffers();
    public List<OfferDtoResponseTwo> getOfferByStatus(OfferStatus status);

    public List<OfferDtoResponseTwo> getOwnOffers(String email);

    public Boolean updateOffer(String action, Long id);
}

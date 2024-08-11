package ma.myrh.mapper;

import ma.myrh.dtos.OfferDtoRequest;
import ma.myrh.dtos.OfferDtoResponse;
import ma.myrh.dtos.OfferDtoResponseTwo;
import ma.myrh.entities.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    OfferMapper MAPPER = Mappers.getMapper(OfferMapper.class);

    Offer offerDtoRequestToOffer(OfferDtoRequest offerDtoRequest);
    OfferDtoResponse offerToOfferDtoResponse(Offer offer);
    OfferDtoResponseTwo offerToOfferDtoResponseTwo(Offer offer);
    List<OfferDtoResponse> listOfferDtoListOfferR(List<Offer> offers);
    List<OfferDtoResponseTwo> ListOfferToListOfferDtoResponseTwo(List<Offer> offers);
}

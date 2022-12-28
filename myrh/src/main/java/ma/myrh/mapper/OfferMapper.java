package ma.myrh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {
    OfferMapper MAPPER = Mappers.getMapper(OfferMapper.class);
}

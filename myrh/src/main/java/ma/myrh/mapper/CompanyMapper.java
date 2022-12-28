package ma.myrh.mapper;

import ma.myrh.dtos.CompanyDto;
import ma.myrh.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper
public interface CompanyMapper {

    CompanyMapper Mapper = Mappers.getMapper(CompanyMapper.class);

    CompanyDto companyDto(Company company);

}

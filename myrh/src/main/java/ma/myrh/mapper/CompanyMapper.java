package ma.myrh.mapper;

import ma.myrh.dtos.CompanyDtoRequest;
import ma.myrh.dtos.CompanyDtoResponse;
import ma.myrh.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper Mapper = Mappers.getMapper(CompanyMapper.class);

    CompanyDtoResponse companyToCompanyDtoResponse(Company company);

    Company companyDto(CompanyDtoRequest companyDtoRequest);

    CompanyDtoRequest companyToCompanyRequest(Company company);





}

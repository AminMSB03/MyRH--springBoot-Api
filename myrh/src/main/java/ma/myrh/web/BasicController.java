package ma.myrh.web;

import ma.myrh.dtos.CompanyDto;
import ma.myrh.entities.Company;
import ma.myrh.mapper.CompanyMapper;
import ma.myrh.services.companyService.CompanyService;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BasicController {


    CompanyService companyService;
    CompanyMapper companyTargetMapper;

    public BasicController(CompanyService companyService) {
        this.companyService = companyService;
        this.companyTargetMapper = Mappers.getMapper(CompanyMapper.class);
    }

    @GetMapping("/hello")
    public String login(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }else{
            username = principal.toString();
        }
        Company company = this.companyService.login(username);
        CompanyDto companyDto = this.companyTargetMapper.companyDto(company);
        System.out.println(companyDto);
        return username;
    }

}

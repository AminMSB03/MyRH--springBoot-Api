package ma.myrh.web;

import lombok.extern.slf4j.Slf4j;
import ma.myrh.dtos.CompanyDtoRequest;
import ma.myrh.dtos.CompanyDtoResponse;
import ma.myrh.services.companyService.CompanyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CompanyRestController {

    CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/companies",method =RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompanyDtoResponse getCompany(@ModelAttribute CompanyDtoRequest companyDtoRequest) throws IOException {
        CompanyDtoResponse register = this.companyService.register(companyDtoRequest);
        return register;
    }

    @RequestMapping("companies")
    public CompanyDtoResponse getCompany(Principal principal){
        CompanyDtoResponse companyByEmail = this.companyService.getCompanyByEmail(principal.getName());
        return companyByEmail;
    }


}

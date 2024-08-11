package ma.myrh.repositories;

import ma.myrh.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByEmail(String email);
}

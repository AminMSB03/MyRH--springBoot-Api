package ma.myrh.repositories;

import ma.myrh.entities.Company;
import ma.myrh.entities.Offer;
import ma.myrh.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OfferRepository extends JpaRepository<Offer,Long> {

    List<Offer> findOfferByStatusIs(OfferStatus status) ;
    List<Offer> findOfferByCompany(Company company);

}

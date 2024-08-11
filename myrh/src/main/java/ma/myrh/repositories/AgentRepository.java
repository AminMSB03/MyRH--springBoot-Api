package ma.myrh.repositories;

import ma.myrh.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByEmail(String email);

}

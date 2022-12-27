package ma.myrh.services.agentService;


import ma.myrh.dtos.AgentRepository;
import ma.myrh.entities.Agent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service public class AgentServiceImpl implements AgentService{

    AgentRepository agentRepository;

    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


    @Override
    public Agent Login(String email) {
        Optional<Agent> agentOptional = this.agentRepository.findByEmail(email);
        if(agentOptional.isPresent()){
            return agentOptional.get();
        }
        return null;
    }
}

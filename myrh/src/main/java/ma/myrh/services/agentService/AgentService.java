package ma.myrh.services.agentService;

import ma.myrh.entities.Agent;
import org.springframework.stereotype.Service;


public interface AgentService {
    Agent Login(String email);
}

package ma.myrh.dtos;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class AgentDto {
    String email;
    String password;
}

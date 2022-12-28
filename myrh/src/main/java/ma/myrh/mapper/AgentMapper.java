package ma.myrh.mapper;

import ma.myrh.dtos.AgentDto;
import ma.myrh.entities.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgentMapper {

    AgentMapper MAPPER = Mappers.getMapper(AgentMapper.class);

    AgentDto entityToDTO(Agent agent);
}

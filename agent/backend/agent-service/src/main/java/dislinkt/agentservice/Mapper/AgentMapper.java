package dislinkt.agentservice.Mapper;

import java.util.ArrayList;

import org.mapstruct.Mapper;

import dislinkt.agentclient.AgentDTO;
import dislinkt.agentservice.Entity.Agent;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    AgentDTO entityToDto(Agent agent);

    Agent dtoToEntity(AgentDTO agentDTO);

    ArrayList<AgentDTO> toAgentDTOList(ArrayList<AgentDTO> findByFirstName);

    
}

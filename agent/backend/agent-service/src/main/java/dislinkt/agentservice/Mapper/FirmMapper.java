package dislinkt.agentservice.Mapper;

import java.util.ArrayList;

import org.mapstruct.Mapper;

import dislinkt.agentclient.FirmDTO;
import dislinkt.agentservice.Entity.Firm;

@Mapper(componentModel = "spring")
public interface FirmMapper {

	FirmDTO entityToDto(Firm firm);

	ArrayList<FirmDTO> entityListToDtoList(ArrayList<Firm> firmList);

	Firm dtoToEntity(FirmDTO firmDTO);

	ArrayList<FirmDTO> toFirmDTOList(ArrayList<FirmDTO> findByFirstName);

}

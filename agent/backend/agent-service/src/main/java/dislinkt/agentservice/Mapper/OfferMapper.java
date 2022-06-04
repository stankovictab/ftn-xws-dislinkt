package dislinkt.agentservice.Mapper;

import java.util.ArrayList;

import org.mapstruct.Mapper;

import dislinkt.agentclient.OfferDTO;
import dislinkt.agentservice.Entity.Offer;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    
    OfferDTO entityToDto(Offer offer);

    Offer dtoToEntity(OfferDTO offerDTO);

    ArrayList<OfferDTO> toOfferDTOList(ArrayList<OfferDTO> findByFirstName);
        
}

package dislinkt.agentservice.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dislinkt.agentclient.OfferDTO;
import dislinkt.agentservice.Entity.Firm;
import dislinkt.agentservice.Entity.Offer;
import dislinkt.agentservice.Mapper.OfferMapper;
import dislinkt.agentservice.Repository.OfferRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;


    public OfferDTO createOffer(OfferDTO offerDTO, Firm firm) {
        Offer offer = offerMapper.dtoToEntity(offerDTO);
        offer.setCreationDate(LocalDateTime.now());

        if (!offer.getDislinktShare()) {
            if (offerRepository.save(offer) != null) {
                System.out.println("Offer created");
                return offerMapper.entityToDto(offer);
            }
            System.out.println("Offer not created");
            return null;
        }
        else if (firm.getApiToken() != null) {
            if (offerRepository.save(offer) != null) {
                System.out.println("Offer created");
                return offerMapper.entityToDto(offer);
            }
            System.out.println("Offer not created");
            return null;
            
        }
        return null;
    }
    
}

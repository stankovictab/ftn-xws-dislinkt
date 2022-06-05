package dislinkt.agentservice.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    // TODO: FOR TESTING PURPOSES ONLY
    public Offer generateOffer(String firmId) {
        Offer offer = new Offer();
        offer.setFirmId(firmId);
        offer.setJobTitle("JobTitle");
        offer.setJobDescription("JobDescription");
        offer.setJobLocation("JobLocation");
        offer.setJobSeniority("Medior");
        offer.setJobField("Backend");

        ArrayList<String> tech = new ArrayList<String>();
        tech.add("Java");
        tech.add("C++");
        offer.setJobTechnologies(tech);

        ArrayList<String> resp = new ArrayList<String>();
        resp.add("Resp1");
        resp.add("Resp2");
        offer.setJobResponsibilities(resp);

        ArrayList<String> req = new ArrayList<String>();
        req.add("Req1");
        req.add("Req2");
        offer.setJobRequirements(req);

        ArrayList<String> bon = new ArrayList<String>();
        bon.add("Bon1");
        bon.add("Bon2");
        offer.setJobBonuses(bon);

        offer.setDislinktShare(true);

        return offerRepository.save(offer);
    }

    public ArrayList<OfferDTO> getAllOffers() {
        ArrayList<Offer> offers = offerRepository.findAll();
        ArrayList<OfferDTO> offerDTOs = new ArrayList<>();
        for (Offer offer : offers) {
            offerDTOs.add(offerMapper.entityToDto(offer));
        }
        return offerDTOs;
    }


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

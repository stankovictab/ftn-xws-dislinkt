package dislinkt.agentservice.Service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dislinkt.agentclient.FirmDTO;
import dislinkt.agentservice.Entity.Agent;
import dislinkt.agentservice.Entity.Firm;
import dislinkt.agentservice.Mapper.FirmMapper;
import dislinkt.agentservice.Repository.AgentRepository;
import dislinkt.agentservice.Repository.FirmRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FirmService {

    private final FirmRepository firmRepository;

    private final FirmMapper firmMapper;

    private final AgentRepository agentRepository;

    // TODO: FOR TESTING PURPOSES ONLY
    public Firm generateFirm(String agentId) {
        Firm firm = new Firm();
        firm.setOwnerId(agentId);
        firm.setEmail("email@email.email");
        firm.setNumber("1");
        firm.setName("FirmName");
        firm.setDescription("FirmDescription");
        firm.setCulture("FirmCulture");

        return firmRepository.save(firm);
    }

    public Firm getByName(String name) {
        if (name == null) {
            System.out.println("FirmService.getByName: name is null");
            return null;
        }
        ArrayList<Firm> firm = firmRepository.findByName(name);
        if (firm == null || firm.isEmpty()) {
            System.out.println("FirmService.getByName: firm is null");
            return null;
        }
        return firm.get(0);
    }

    public Firm getFirm(String firmId) {
        return firmRepository.findById(firmId).orElse(null);
    }

    public ArrayList<Firm> getAllFirms() {
        return firmRepository.findAll();
    }

    public Firm setApiToken(String firmId, String apiToken) {
        Firm firm = firmRepository.getById(firmId);
        if (firm != null) {
            firm.setApiToken(apiToken);
            firmRepository.save(firm);
            return firm;
        }
        return null;
    }

    public FirmDTO updateFirm(FirmDTO firmDTO) {
        Firm firm = firmRepository.findById(firmDTO.getId()).get();
        if (firm == null) {
            System.out.println("Firm not found");
            return null;
        }
        if (firmDTO.getEmail() != null && !firm.getEmail().equals(firmDTO.getEmail())) {
            firm.setEmail(firmDTO.getEmail());
            System.out.println("Email changed");
        }
        if (firmDTO.getNumber() != null && !firm.getNumber().equals(firmDTO.getNumber())) {
            firm.setNumber(firmDTO.getNumber());
            System.out.println("Number changed");
        }
        if (firmDTO.getName() != null && !firm.getName().equals(firmDTO.getName())) {
            firm.setName(firmDTO.getName());
            System.out.println("Name changed");
        }
        if (firmDTO.getDescription() != null && !firm.getDescription().equals(firmDTO.getDescription())) {
            firm.setDescription(firmDTO.getDescription());
            System.out.println("Description changed");
        }
        if (firmDTO.getCulture() != null && !firm.getCulture().equals(firmDTO.getCulture())) {
            firm.setCulture(firmDTO.getCulture());
            System.out.println("Culture changed");
        }
        if (firmRepository.save(firm) != null) {
            System.out.println("Firm updated");
            return firmMapper.entityToDto(firm);
        }
        System.out.println("Firm not updated");
        return null;
    }

    public String approveFirm(String firmId) {
        Firm firm = firmRepository.findById(firmId).get();
        if (firm == null) {
            System.out.println("Firm not found");
            return null;
        }
        if (firm.getApproved()) {
            System.out.println("Firm already approved");
            return "";
        }
        firm.setApproved(true);
        if (firmRepository.save(firm) != null) {
            System.out.println("Firm approved");
            return firm.getOwnerId();
        }
        System.out.println("Firm not approved");
        return "";
    }

    public ArrayList<FirmDTO> getUnapproved() {
        ArrayList<Firm> firms = firmRepository.findByApproved(false);
        ArrayList<FirmDTO> firmDTOs = new ArrayList<>();

        for (Firm firm : firms) {
            firmDTOs.add(firmMapper.entityToDto(firm));
        }

        return firmDTOs;
    }

    public FirmDTO firmRegister(FirmDTO firmDTO) {
        Firm firm = firmMapper.dtoToEntity(firmDTO);
        if (firmRepository.findByOwnerId(firm.getOwnerId()) != null) {
            System.out.println("User already has firm");
            return firmDTO;
        }
        firm.setOfferIds(null);
        firm.setFirmCommentIds(null);
        firm.setApproved(false);

        ArrayList<Firm> firms = firmRepository.findByName(firm.getName());
        if (!firms.isEmpty()) {
            System.out.println("Firm with name: '" + firm.getName() + "' already exists.");
            return null;
        }

        if (firmRepository.save(firm) != null) {
            System.out.println("Firm with name: '" + firm.getName() + "' successfully saved.");
            System.out.println("Administrator will be able to approve it.");
            Agent agent = agentRepository.findById(firm.getOwnerId()).get();
            agent.setFirmId(firm.getId());
            agentRepository.save(agent);
            return firmMapper.entityToDto(firm);
        }
        System.out.println("Firm with name: '" + firm.getName() + "' could not be saved.");
        return null;
    }

}

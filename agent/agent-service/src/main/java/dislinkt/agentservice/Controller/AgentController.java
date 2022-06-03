package dislinkt.agentservice.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dislinkt.agentclient.AgentDTO;
import dislinkt.agentclient.AgentServiceFeignClient;
import dislinkt.agentclient.CommentDTO;
import dislinkt.agentclient.FirmDTO;
import dislinkt.agentclient.OfferDTO;
import dislinkt.agentservice.Entity.Agent;
import dislinkt.agentservice.Mapper.AgentMapper;
import dislinkt.agentservice.Service.AgentService;
import dislinkt.agentservice.Service.CommentService;
import dislinkt.agentservice.Service.FirmService;
import dislinkt.agentservice.Service.OfferService;
import dislinkt.postclient.PostDTO;
import dislinkt.postclient.PostServiceFeignClient;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AgentController implements AgentServiceFeignClient {

	private final AgentService agentService;

	private final FirmService firmService;

	private final OfferService offerService;

	private final CommentService commentService;

	private final AgentMapper agentMapper;

	private final PostServiceFeignClient postServiceFeignClient;


	@GetMapping(value = "/actuator/info")
	@Override
	public String home() {
		return "Hello from User Service";
	}

	@Override
	public ResponseEntity<AgentDTO> setApiToken(Map<String, String> json) {
		String userId = json.get("agentId");
		String apiToken = json.get("apiToken");

		if (userId == null || apiToken == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Agent agent = agentService.setApiToken(userId, apiToken);
		if (agent != null) {
			return new ResponseEntity<>(agentMapper.entityToDto(agent), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Override
	public void setAdmin() {
		agentService.setAdmin("agent3");
	}

	@Override
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		Agent agent = agentService.getAgent(commentDTO.getUserId());
		if (agent.getRole().equals("User")) {
			CommentDTO commentDTO2 = commentService.createComment(commentDTO);
			if (commentDTO2 != null) {
				return new ResponseEntity<>(commentDTO2, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			System.out.println("Not a regular user");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


	@Override
	public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
		Agent agent = agentService.getAgent(firmService.getFirm(offerDTO.getFirmId()).getOwnerId());
		OfferDTO offer = offerService.createOffer(offerDTO, agent);
		if (offer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (offer.getDislinktShare()) {
			// TODO: 
			PostDTO postDTO = new PostDTO();
			postDTO.setTitle("Job Offer");
			postDTO.setOfferId(offer.getId());
			postDTO.setFirmId(offer.getFirmId());
			postDTO.setJobTitle(offer.getJobTitle());
			postDTO.setJobDescription(offer.getJobDescription());
			postDTO.setJobLocation(offer.getJobLocation());
			postDTO.setJobSeniority(offer.getJobSeniority());
			postDTO.setJobField(offer.getJobField());
			postDTO.setJobTechnologies(offer.getJobTechnologies());
			postDTO.setJobResponsibilities(offer.getJobResponsibilities());
			postDTO.setJobRequirements(offer.getJobRequirements());
			
			postDTO.setEmbedLink("localhost:5003/post/getPost/" + offer.getId());
			postDTO.setApiToken(agent.getApiToken());

			postServiceFeignClient.createPost(postDTO);
		}

		return new ResponseEntity<>(offer, HttpStatus.OK);
	}

	@Override 
	public ResponseEntity<FirmDTO> updateFirm(@RequestBody FirmDTO firmDTO) {
		FirmDTO firm = firmService.updateFirm(firmDTO);
		if (firm == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(firm, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> approveFirm(@RequestBody Map<String, String> ids) {
		String firmId = ids.get("firmId");
		String adminId = ids.get("adminId");
		if (agentService.getAgent(adminId).getRole().equals("Admin")) {
			String result = firmService.approveFirm(firmId);
			if (result == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Boolean result2 = agentService.setOwner(result);
			if (result2 == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else if (result2) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(false, HttpStatus.INSUFFICIENT_STORAGE);
			}
		}
		else {
			System.out.println("You are not an admin");
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public ResponseEntity<ArrayList<FirmDTO>> getUnapproved() {
		ArrayList<FirmDTO> firmDTOs = firmService.getUnapproved();

		if (firmDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(firmDTOs, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<FirmDTO> firmRegister(@RequestBody FirmDTO firmDTO) {
		FirmDTO firmDTO1 = firmService.firmRegister(firmDTO);
		if (firmDTO1 == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(firmDTO1, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> checkUsername(@RequestBody String username) {
		Boolean isUsernameAvailable = agentService.checkUsername(username);
		if (isUsernameAvailable == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(isUsernameAvailable, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AgentDTO> login(@RequestBody AgentDTO incomingAgent) {
		Agent agent = agentService.login(incomingAgent);
		if (agent == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		AgentDTO agentDTO = agentMapper.entityToDto(agent);
		return new ResponseEntity<>(agentDTO, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<AgentDTO> agentRegister(@RequestBody AgentDTO incomingAgent) {
		Agent agent = agentService.register(incomingAgent);
		if (agent == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		AgentDTO agentDTO = agentMapper.entityToDto(agent);
		return new ResponseEntity<>(agentDTO, HttpStatus.OK);
	}
	
}

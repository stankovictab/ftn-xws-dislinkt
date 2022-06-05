package dislinkt.agentclient;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "agent-service")
public interface AgentServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	@GetMapping(value = "/actuator/info")
	public String home();

	@PostMapping("/offer/getAll")
	public ResponseEntity<ArrayList<OfferDTO>> getAllOffers();

	@PostMapping("/agent/generateAgents")
	public void generateAgents();

	@PostMapping("/firm/findByName")
	public ResponseEntity<FirmDTO> findByName(@RequestBody String name);

	@PostMapping("/firm/searchFirm")
	public ResponseEntity<ArrayList<FirmDTO>> searchFirms(@RequestBody String query);

	@PostMapping("/firm/setApiToken")
	public ResponseEntity<FirmDTO> setFirmApiToken(@RequestBody Map<String, String> json);

	@PostMapping("/agent/setApiToken")
	public ResponseEntity<AgentDTO> setApiToken(@RequestBody Map<String, String> json);

	@PostMapping("/agent/setAdmin")
	public void setAdmin();

	@PostMapping("/comment/create")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO);

	@PostMapping("/offer/create")
	public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO);

	@PostMapping("/firm/get")
	public ResponseEntity<FirmDTO> getFirm(@RequestBody String id);

	@PostMapping("/firm/update")
	public ResponseEntity<FirmDTO> updateFirm(@RequestBody FirmDTO firmDTO);

	@PostMapping("/firm/approve")
	public ResponseEntity<Boolean> approveFirm(@RequestBody Map<String, String> ids);

	@PostMapping("/firm/getUnapproved")
	public ResponseEntity<ArrayList<FirmDTO>> getUnapproved();

	@PostMapping("/firm/register")
	public ResponseEntity<FirmDTO> firmRegister(@RequestBody FirmDTO firmDTO);

	// @PostMapping("/agent/becomeOwner")
	// public ResponseEntity<AgentDTO> becomeOwner(@RequestBody AgentDTO agentDTO);

	@PostMapping("/agent/checkUsername")
	public ResponseEntity<Boolean> checkUsername(@RequestBody String username);

	@PostMapping("/agent/login")
	public ResponseEntity<AgentDTO> login(@RequestBody AgentDTO agentDTO);

	@PostMapping("/agent/register")
	public ResponseEntity<AgentDTO> agentRegister(@RequestBody AgentDTO agentDTO);

}

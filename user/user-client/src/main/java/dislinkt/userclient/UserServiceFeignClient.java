package dislinkt.userclient;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "user-service")
public interface UserServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	@GetMapping(value = "/actuator/info")
	public String home();

	@PostMapping(value = "/user/findByName")
	public ResponseEntity<ArrayList<UserDTO>> findByName(@RequestBody Map<String, String> json);

	@PostMapping(value = "/user/findByFirstName")
	public ResponseEntity<ArrayList<UserDTO>> findByFirstName(@RequestBody String firstName);

	@PostMapping(value = "/user/findByLastName")
	public ResponseEntity<ArrayList<UserDTO>> findByLastName(@RequestBody String lastName);
	
	@PostMapping(value = "/user/findByUsername")
	public ResponseEntity<UserDTO> findByUsername(@RequestBody String username);

	@PostMapping(value = "/user/findAll")
	public ResponseEntity<ArrayList<UserDTO>> findAll();

	@PostMapping(value = "/user/checkUsername")
	public ResponseEntity<Boolean> checkUsername(@RequestBody String username);

	@PostMapping(value = "/user/updateUsername")
	public ResponseEntity<UserDTO> updateUsername(@RequestBody Map<String, String> json);

	@PostMapping("/user/update")
	public ResponseEntity<UserDTO> userUpdate(@RequestBody UserDTO userDTO);

	@PostMapping("/user/login")
	public ResponseEntity<UserDTO> userLogin(@RequestBody UserDTO userDTO);

	@PostMapping("/user/register")
	public ResponseEntity<UserDTO> userRegister(@RequestBody UserDTO userDTO);

	// @PostMapping(value = "/create/{title}/{description}")
	// public String create(@PathVariable("title") String title,
	// @PathVariable("description") String description);
}

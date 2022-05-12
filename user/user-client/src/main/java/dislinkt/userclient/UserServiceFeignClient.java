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

	@PostMapping(value = "/user/generateUsers")
	public void generateUsers();

	@PostMapping(value = "/user/getAllUserIds")
	public ResponseEntity<ArrayList<String>> getAllUserIds();

	@PostMapping(value = "/user/getConnectionUserIds")
	public ResponseEntity<ArrayList<String>> getConnectionUserIds(@RequestBody String userId);
	
	@PostMapping(value = "/user/blockUser")
	public ResponseEntity<Boolean> blockUser(@RequestBody Map<String, String> userIds);

	// TODO: ovo sranje ne radi iz nekog razloga
	// @PostMapping(value = "/user/updatePrivacy")
	// public ResponseEntity<Boolean> updatePrivacy(@RequestBody String userId);
	// @PostMapping(value = "/user/updatePrivacy/{userId}")
	// public ResponseEntity<Boolean> updatePrivacy(@PathVariable String userId);

	@PostMapping(value = "/user/approveFollow")
	public ResponseEntity<Boolean> approveFollow(@RequestBody Map<String, String> userIds);

	@PostMapping(value = "/user/follow")
	public ResponseEntity<Boolean> followUser(@RequestBody Map<String, String> userIds);

	@PostMapping(value = "/user/viewUser")
	public ResponseEntity<UserDTO> viewUser(@RequestBody Map<String, String> userIds);

	@PostMapping(value = "/user/find")
	public ResponseEntity<ArrayList<UserDTO>> find(@RequestBody Map<String, String> searchTerm);

	@PostMapping(value = "/user/findByName")
	public ResponseEntity<ArrayList<UserDTO>> findByName(@RequestBody Map<String, String> json);

	@PostMapping(value = "/user/findByFirstName")
	public ResponseEntity<ArrayList<UserDTO>> findByFirstName(@RequestBody String firstName);

	@PostMapping(value = "/user/findByLastName")
	public ResponseEntity<ArrayList<UserDTO>> findByLastName(@RequestBody String lastName);
	
	@PostMapping(value = "/user/findByUsername")
	public ResponseEntity<ArrayList<UserDTO>> findByUsername(@RequestBody String username);

	@PostMapping(value = "/user/findById")
	public ResponseEntity<UserDTO> findById(@RequestBody String userId);

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

	
}

package dislinkt.userclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "user-service")
public interface UserServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	@GetMapping(value = "/actuator/info")
	public String home();

	@PostMapping("/user/login")
	public ResponseEntity<UserDTO> userLogin(RequestEntity<UserDTO> user1);

	@PostMapping("/user/register")
	public ResponseEntity<UserDTO> userRegister(RequestEntity<UserDTO> user1);

	// @PostMapping(value = "/create/{title}/{description}")
	// public String create(@PathVariable("title") String title,
	// @PathVariable("description") String description);
}

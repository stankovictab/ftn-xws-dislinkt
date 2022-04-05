package dislinkt.userclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "user-service")
public interface UserServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	// @GetMapping(value = "/actuator/info")
	// public String home();

	// @PostMapping(value = "/create/{title}/{description}")
	// public String create(@PathVariable("title") String title,
	// @PathVariable("description") String description);
}

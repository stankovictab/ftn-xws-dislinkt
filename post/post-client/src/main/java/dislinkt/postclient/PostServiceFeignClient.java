package dislinkt.postclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "post-service")
public interface PostServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	@GetMapping(value = "/actuator/info")
	public String home();
}

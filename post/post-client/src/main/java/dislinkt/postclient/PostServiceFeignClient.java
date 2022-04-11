package dislinkt.postclient;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "post-service")
public interface PostServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	@GetMapping(value = "/actuator/info")
	public String home();
}

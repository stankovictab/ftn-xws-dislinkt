package dislinkt.postclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// This Feign Client will be accessed by other microservices,
// in order to communicate with this one

@FeignClient(value = "post-service")
public interface PostServiceFeignClient {

	// Eureka Dashboard redirects to this URL
	@GetMapping(value = "/actuator/info")
	public String home();

	@PostMapping(value = "/post/create")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO);

	@PostMapping(value = "/comment/create")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO);

	@PostMapping(value = "/post/like/{postId}/{userId}")
	public void likePost(@PathVariable String postId, @PathVariable String userId);

	@PostMapping(value = "/post/unlike/{postId}/{userId}")
	public void dislikePost(@PathVariable String postId, @PathVariable String userId);
	
}

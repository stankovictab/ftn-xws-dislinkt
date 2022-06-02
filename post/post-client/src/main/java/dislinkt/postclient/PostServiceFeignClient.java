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

	@PostMapping(value = "/post/generatePosts")
	public void generatePosts();

	@PostMapping(value = "/post/getFeed") 
	public ResponseEntity<ArrayList<PostDTO>> getFeed(@RequestBody String userId);

	@PostMapping(value = "/post/getAll")
	public ResponseEntity<ArrayList<PostDTO>> getAllByUser(@RequestBody Map<String, String> userIds);

	@PostMapping(value = "/post/create")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO);

	@PostMapping(value = "/comment/create")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO);

	@PostMapping(value = "/post/like")
	public void likePost(@RequestBody Map<String, String> json);

	@PostMapping(value = "/post/unlike")
	public void dislikePost(@RequestBody Map<String, String> json);
	
}

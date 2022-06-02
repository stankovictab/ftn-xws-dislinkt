package dislinkt.postservice.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dislinkt.postclient.CommentDTO;
import dislinkt.postclient.PostDTO;
import dislinkt.postclient.PostServiceFeignClient;
import dislinkt.postservice.Service.CommentService;
import dislinkt.postservice.Service.PostService;
import dislinkt.userclient.UserDTO;
import dislinkt.userclient.UserServiceFeignClient;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController implements PostServiceFeignClient {

	private final PostService postService;

	private final CommentService commentService;

	private final UserServiceFeignClient userServiceFeignClient;

	@Override
	public String home() {
		return "Hello from Post Service";
	}

	@Override
	public void generatePosts() {
		ArrayList<String> userIds = userServiceFeignClient.getAllUserIds().getBody();
		postService.generatePosts(userIds);
	}

	@Override
	public ResponseEntity<ArrayList<PostDTO>> getFeed(@RequestBody String userId) {
		ArrayList<String> connectionUserIds = userServiceFeignClient.getConnectionUserIds(userId).getBody();
		return new ResponseEntity<>(postService.getFeed(userId, connectionUserIds), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ArrayList<PostDTO>> getAllByUser(@RequestBody Map<String, String> userIds) {
		String userId = userIds.get("userId");
		String userPostsId = userIds.get("userPostsId");
		Boolean isPrivate;
		if (!userId.equals(userPostsId)) {
			if (userServiceFeignClient.findById(userPostsId).getBody().getPrivateAccount() != null) {
				isPrivate = userServiceFeignClient.findById(userPostsId).getBody().getPrivateAccount();
			} else {
				isPrivate = false;
			}

			if (isPrivate) {
				UserDTO userDTO = userServiceFeignClient.findById(userId).getBody();
				if (userDTO.getConnectionUserIds().contains(userPostsId)) {
					return new ResponseEntity<>(postService.getAllByUser(userPostsId), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			}
		}
		ArrayList<PostDTO> postDTOs = postService.getAllByUser(userPostsId);
		if (postDTOs == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(postDTOs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
		if (postDTO.getApiToken() != null) {
			String userId = userServiceFeignClient.getUserIdByApiToken(postDTO.getApiToken()).getBody();
			if (userId != null) {
				postDTO.setUserId(userId);
				return new ResponseEntity<>(postService.create(postDTO), HttpStatus.OK);
			}
		}
		PostDTO createdPost = postService.create(postDTO);
		if (createdPost == null) {
			System.out.println("Create: No post created.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PostDTO>(createdPost, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		CommentDTO createdComment = commentService.create(commentDTO);
		if (createdComment == null) {
			System.out.println("Create: No comment created.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CommentDTO>(createdComment, HttpStatus.OK);
	}

	@Override
	public void likePost(@RequestBody Map<String, String> json) {
		postService.likePost(json.get("postId"), json.get("userId"));
	}

	@Override
	public void dislikePost(@RequestBody Map<String, String> json) {
		postService.dislikePost(json.get("postId"), json.get("userId"));
	}

}

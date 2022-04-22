package dislinkt.post.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dislinkt.post.Entity.CommentDTO;
import dislinkt.post.Entity.PostDTO;
import dislinkt.post.Service.CommentService;
import dislinkt.post.Service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    
    public String home() {
        return "Hello from Post Service";
    }

    
    public ResponseEntity<ArrayList<PostDTO>> getAllByUser(String userId) {
        ArrayList<PostDTO> postDTOs = postService.getAllByUser(userId);    
        if (postDTOs == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
    }

    
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {        
        PostDTO createdPost = postService.create(postDTO);
        if (createdPost == null) {
            System.out.println("Create: No post created.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PostDTO>(createdPost, HttpStatus.OK);
    }

    
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO createdComment = commentService.create(commentDTO);
        if (createdComment == null) {
            System.out.println("Create: No comment created.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CommentDTO>(createdComment, HttpStatus.OK);
    }

    
	public void likePost(@RequestBody Map<String, String> json) {
        postService.likePost(json.get("postId"), json.get("userId"));
    }

    
    public void dislikePost(@RequestBody Map<String, String> json) {
        postService.dislikePost(json.get("postId"), json.get("userId"));
    }    

}

package dislinkt.postservice.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dislinkt.postclient.PostDTO;
import dislinkt.postservice.Entity.Post;
import dislinkt.postservice.Mapper.PostMapper;
import dislinkt.postservice.Repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public ArrayList<PostDTO> getAllByUser(String userId) {
        ArrayList<Post> posts = postRepository.findAllByUserId(userId);
        ArrayList<PostDTO> postDTOs = new ArrayList<>();
        if (posts == null) {
            System.out.println("GetAllByUser: No posts found.");
            return null;
        }
        for (Post post : posts) {
            postDTOs.add(postMapper.entityToDto(post));
        }
        return postDTOs;
    }

    public PostDTO create(PostDTO postDTO) {
        Post post = postMapper.dtoToEntity(postDTO);
        LocalDateTime now = LocalDateTime.now();
        post.setCreationDate(now);
        post.setLikes(0);
        post.setDislikes(0);
        post.setLikedUserIds(null);
        post.setDislikedUserIds(null);
        post = postRepository.save(post);
        if (post != null) {
            System.out.println("Create: Post successfully saved.");
            return postMapper.entityToDto(post);
        }
        return null;
    }

    public void likePost(String postId, String userId) {
        Post post = postRepository.findById(postId).get();     
        if (post != null) {
            if (post.getLikedUserIds() == null) {
                post.setLikedUserIds(new ArrayList<String>());
            }
            else if (post.getLikedUserIds().contains(userId)) {
                System.out.println("Like: User already liked this post.");
                return;
            }
            post.setLikes(post.getLikes() + 1);
            post.getLikedUserIds().add(userId);
            if (postRepository.save(post) != null) {
                System.out.println("Like: Post successfully liked.");
            }
            else {
                System.out.println("Like: Post could not be liked.");
            }
        }
        else {
            System.out.println("Like: No post found.");
        }
    }

    public void dislikePost(String postId, String userId) {
        Post post = postRepository.findById(postId).get();     
        if (post != null) {
            ArrayList<String> dislikedUserIds = post.getDislikedUserIds();
            if (dislikedUserIds == null) {
                dislikedUserIds = new ArrayList<>();
            }
            else if (dislikedUserIds.contains(userId)) {
                System.out.println("Dislike: User already disliked this post.");
                return;
            }
            post.setDislikes(post.getDislikes() + 1);
            dislikedUserIds.add(userId);
            post.setDislikedUserIds(dislikedUserIds);
            postRepository.save(post);
        }
        else {
            System.out.println("Dislike: No post found.");
        }
    }
}

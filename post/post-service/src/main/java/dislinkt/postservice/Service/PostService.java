package dislinkt.postservice.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dislinkt.postclient.PostDTO;
import dislinkt.postservice.Entity.Image;
import dislinkt.postservice.Entity.Post;
import dislinkt.postservice.Mapper.PostMapper;
import dislinkt.postservice.Repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	private final PostMapper postMapper;

	private final ImageService imageService;

	public void generatePosts(ArrayList<String> userIds) {

		// TODO: Moze ove dve funckije u ./up.sh
		for (int i = 0; i < 33; i++) {
			Post post = new Post();
			post.setTitle("Post " + i);
			post.setDescription("Description " + i);
			post.setUserId(userIds.get(0));
			create(postMapper.entityToDto(post), null);
		}
		for (int i = 0; i < 33; i++) {
			Post post = new Post();
			post.setTitle("Post " + i);
			post.setDescription("Description " + i);
			post.setUserId(userIds.get(1));
			create(postMapper.entityToDto(post), null);
		}
		for (int i = 0; i < 33; i++) {
			Post post = new Post();
			post.setTitle("Post " + i);
			post.setDescription("Description " + i);
			post.setUserId(userIds.get(2));
			create(postMapper.entityToDto(post), null);
		}
	}

	public ArrayList<PostDTO> getFeed(String userId, ArrayList<String> connectionUserIds) {
		ArrayList<PostDTO> postDTOs = new ArrayList<>();

		for (String followingUserId : connectionUserIds) {
			ArrayList<Post> posts = postRepository.findAllByUserId(followingUserId);
			for (Post post : posts) {
				postDTOs.add(postMapper.entityToDto(post));
			}
		}
		if (postDTOs.isEmpty()) {
			System.out.println("GetFeed: No posts found.");
			return null;
		}
		postDTOs.sort(Comparator.comparing(
				(PostDTO postDTO) -> postDTO
						.getCreationDate()
						.toLocalDate())
				.reversed()
				.thenComparing(Comparator.comparing(
						(PostDTO postDTO) -> postDTO
								.getCreationDate()
								.toLocalTime())));
		return postDTOs;
	}

	public ArrayList<PostDTO> getAllByUser(String userId) {
		ArrayList<Post> posts = postRepository.findAllByUserId(userId);
		ArrayList<PostDTO> postDTOs = new ArrayList<>();
		if (posts == null) {
			System.out.println("GetAllByUser: No posts found.");
			return null;
		}
		System.out.println(posts.size());
		for (Post post : posts) {
			System.out.println(post.getTitle());
			postDTOs.add(postMapper.entityToDto(post));
		}
		return postDTOs;
	}

	public PostDTO create(PostDTO postDTO, MultipartFile image) {
		Post post = postMapper.dtoToEntity(postDTO);
		LocalDateTime now = LocalDateTime.now();
		post.setCreationDate(now);
		post.setLikes(0);
		post.setDislikes(0);
		post.setLikedUserIds(null);
		post.setDislikedUserIds(null);
		if (image != null)
		{
			try {
				String imageId = imageService.addImage(post.getImageTitle(), image);
				post.setImageId(imageId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		post = postRepository.save(post);
		if (post != null) {
			if (image != null)
			{
				Image image1 = imageService.getImage(post.getImageId());
				image1.setPostId(post.getId());
				//TODO: 
				// imageService.update(image1)
			}
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
			} else if (post.getLikedUserIds().contains(userId)) {
				System.out.println("Like: User already liked this post.");
				return;
			}
			post.setLikes(post.getLikes() + 1);
			post.getLikedUserIds().add(userId);
			if (postRepository.save(post) != null) {
				System.out.println("Like: Post successfully liked.");
			} else {
				System.out.println("Like: Post could not be liked.");
			}
		} else {
			System.out.println("Like: No post found.");
		}
	}

	public void dislikePost(String postId, String userId) {
		Post post = postRepository.findById(postId).get();
		if (post != null) {
			ArrayList<String> dislikedUserIds = post.getDislikedUserIds();
			if (dislikedUserIds == null) {
				dislikedUserIds = new ArrayList<>();
			} else if (dislikedUserIds.contains(userId)) {
				System.out.println("Dislike: User already disliked this post.");
				return;
			}
			post.setDislikes(post.getDislikes() + 1);
			dislikedUserIds.add(userId);
			post.setDislikedUserIds(dislikedUserIds);
			postRepository.save(post);
		} else {
			System.out.println("Dislike: No post found.");
		}
	}
}

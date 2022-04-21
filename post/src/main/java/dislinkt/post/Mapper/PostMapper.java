package dislinkt.post.Mapper;

import org.mapstruct.Mapper;

import dislinkt.post.Entity.PostDTO;
import dislinkt.post.Entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    
    PostDTO entityToDto(Post post);

    Post dtoToEntity(PostDTO postDTO);
    
}

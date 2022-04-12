package dislinkt.postservice.Mapper;

import org.mapstruct.Mapper;

import dislinkt.postclient.PostDTO;
import dislinkt.postservice.Entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    
    PostDTO entityToDto(Post post);

    Post dtoToEntity(PostDTO postDTO);
    
}

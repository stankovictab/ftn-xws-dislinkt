package dislinkt.post.Mapper;

import org.mapstruct.Mapper;

import dislinkt.post.Entity.CommentDTO;
import dislinkt.post.Entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    
    CommentDTO entityToDto(Comment comment);

    Comment dtoToEntity(CommentDTO commentDTO);

}

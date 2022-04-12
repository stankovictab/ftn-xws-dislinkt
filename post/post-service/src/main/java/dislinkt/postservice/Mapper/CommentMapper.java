package dislinkt.postservice.Mapper;

import org.mapstruct.Mapper;

import dislinkt.postclient.CommentDTO;
import dislinkt.postservice.Entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    
    CommentDTO entityToDto(Comment comment);

    Comment dtoToEntity(CommentDTO commentDTO);

}

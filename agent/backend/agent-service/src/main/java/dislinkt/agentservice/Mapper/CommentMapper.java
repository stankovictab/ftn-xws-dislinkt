package dislinkt.agentservice.Mapper;

import java.util.ArrayList;

import org.mapstruct.Mapper;

import dislinkt.agentclient.CommentDTO;
import dislinkt.agentservice.Entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    
    CommentDTO entityToDto(Comment comment);

    Comment dtoToEntity(CommentDTO commentDTO);

    ArrayList<CommentDTO> toCommentDTOList(ArrayList<CommentDTO> findByFirstName);
        
}

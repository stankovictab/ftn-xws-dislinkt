package dislinkt.agentservice.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dislinkt.agentclient.CommentDTO;
import dislinkt.agentservice.Entity.Comment;
import dislinkt.agentservice.Mapper.CommentMapper;
import dislinkt.agentservice.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;



    public ArrayList<CommentDTO> getAllCommentsByFirmId(String firmId) {
        ArrayList<Comment> comments = commentRepository.findByFirmId(firmId);
        ArrayList<CommentDTO> commentDTOs = new ArrayList<>();

        for (Comment comment : comments) {
            commentDTOs.add(commentMapper.entityToDto(comment));
        }

        return commentDTOs;
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.dtoToEntity(commentDTO);
        comment.setDate(LocalDateTime.now());

        if (commentRepository.save(comment) != null) {
            System.out.println("Comment created");
            return commentMapper.entityToDto(comment);
        }
        System.out.println("Comment not created");
        return null;
    }
    
}

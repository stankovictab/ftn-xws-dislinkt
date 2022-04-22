package dislinkt.post.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dislinkt.post.Entity.CommentDTO;
import dislinkt.post.Entity.Comment;
import dislinkt.post.Mapper.CommentMapper;
import dislinkt.post.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = commentMapper.dtoToEntity(commentDTO);
        LocalDateTime now = LocalDateTime.now();
        comment.setCreationDate(now);
        comment = commentRepository.save(comment);
        if (comment != null) {
            System.out.println("Create: Comment successfully saved.");
            return commentMapper.entityToDto(comment);
        }
        return null;
    }

}

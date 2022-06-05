package dislinkt.postservice.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dislinkt.postclient.CommentDTO;
import dislinkt.postservice.Entity.Comment;
import dislinkt.postservice.Mapper.CommentMapper;
import dislinkt.postservice.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public ArrayList<CommentDTO> getAllByPostId(String postId) {
        ArrayList<CommentDTO> commentDTOs = new ArrayList<>();
        ArrayList<Comment> comments = commentRepository.findAllByPostId(postId);

        if (comments != null && comments.size() > 0) {
            for (Comment comment : comments) {
                commentDTOs.add(commentMapper.entityToDto(comment));
            }
            System.out.println("CommentService.getAllByPostId: found comments");
            return commentDTOs;
        }
        System.out.println("CommentService.getAllByPostId: no comments found");
        return null;
    }

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

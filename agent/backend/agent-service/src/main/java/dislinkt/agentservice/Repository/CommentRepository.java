package dislinkt.agentservice.Repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.agentservice.Entity.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    ArrayList<Comment> findByFirmId(String firmId);
}

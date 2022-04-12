package dislinkt.postservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.postservice.Entity.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
    

}

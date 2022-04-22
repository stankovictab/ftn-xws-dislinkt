package dislinkt.post.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.post.Entity.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
    

}

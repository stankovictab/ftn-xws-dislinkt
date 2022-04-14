package dislinkt.postservice.Repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.postservice.Entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>  {

    ArrayList<Post> findAllByUserId(String userId);

    

}

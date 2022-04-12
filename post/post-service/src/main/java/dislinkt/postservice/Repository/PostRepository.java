package dislinkt.postservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.postservice.Entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>  {

    

}

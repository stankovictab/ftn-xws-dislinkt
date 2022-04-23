package dislinkt.postservice.Repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.postservice.Entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String>  {

    ArrayList<Image> findAllByUserId(String userId);

    

}

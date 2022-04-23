package dislinkt.postservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.postservice.Entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}

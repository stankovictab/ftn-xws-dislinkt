package dislinkt.userservice.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dislinkt.userservice.Entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // $options: 'i' for case insensitive
    @Query("{'username': {$regex: ?0, $options: 'i'}}")
    ArrayList<User> findByUsername(String username);

    @Query("{'firstName': {$regex: ?0, $options: 'i'}}")
    ArrayList<User> findByFirstName(String firstName);

    @Query("{'lastName': {$regex: ?0, $options: 'i'}}")
    ArrayList<User> findByLastName(String lastName);

    Optional<User> findById(String id);

    ArrayList<String> getConnectionUserIdsById(String id);

    User findByApiToken(String apiToken);

    User getById(String id);

}

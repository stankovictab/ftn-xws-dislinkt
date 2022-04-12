package dislinkt.userservice.Repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dislinkt.userservice.Entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'username': ?0}")
    User findByUsername(String username);

    ArrayList<User> findByFirstName(String firstName);

    ArrayList<User> findByLastName(String lastName);
    
}

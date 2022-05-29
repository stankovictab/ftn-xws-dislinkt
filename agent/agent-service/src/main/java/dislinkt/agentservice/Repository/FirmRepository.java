package dislinkt.agentservice.Repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dislinkt.agentservice.Entity.Firm;

@Repository
public interface FirmRepository extends MongoRepository<Firm, String> {

    
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    ArrayList<Firm> findByName(String name);

    ArrayList<Firm> findByApproved(boolean approved);
}

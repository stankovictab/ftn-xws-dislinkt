package dislinkt.agentservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dislinkt.agentservice.Entity.Offer;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

    
}

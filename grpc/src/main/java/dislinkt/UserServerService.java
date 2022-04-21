package dislinkt;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServerService extends UserServiceGrpc.UserServiceImplBase {

	private MongoClient mangoClient = MongoClients.create("mongodb://root:example@mongo:27017/?authSource=admin");
	private MongoDatabase mangoDatabase = mangoClient.getDatabase("dislinkt");
	private MongoCollection<Document> mangoCollection = mangoDatabase.getCollection("users");

	@Override
	public void getUser(UserCredentials request, StreamObserver<UserId> responseObserver) {
		System.out.println("getUser");
		UserId response = UserId.newBuilder().setId("1").build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getUserCredentials(UserId request, StreamObserver<UserCredentials> responseObserver) {
		System.out.println("getUserCredentials");
		System.out.println("request.getId(): " + request.getId());
		
		mangoCollection.find().forEach((result) -> {
			System.out.println("result: " + result);
		});
		
		Bson filter = eq("userId", request.getId());
		Document document = mangoCollection.find(filter).first();
		UserCredentials response = UserCredentials.newBuilder().setUserId(document.getString("userId")).setPassword(document.getString("password")).setUsername(document.getString("username")).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
package dislinkt;

import java.util.Arrays;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;

import org.bson.Document;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServerService extends UserServiceGrpc.UserServiceImplBase {

	// private MongoClient mangoClient =
	// MongoClients.create("mongodb://localhost:27017");
	// private MongoClient mangoClient = MongoClients.create("mongodb://mongo:27017/?authSource=root");
	private MongoClient mangoClient = MongoClients.create("mongodb://root:example@mongo:27017/?authSource=admin");
	// MongoClient mangoClient = MongoClients.create("mongodb://<username>:<password>@<hostname>:<port>/?authSource=<authenticationDb>");

	private MongoDatabase mangoDatabase = mangoClient.getDatabase("dislinkt");
	private MongoCollection<Document> mangoCollection = mangoDatabase.getCollection("users");
	// private MongoClient mangoClient;// = MongoClients.create("mongodb://mongo:27017");
	// private MongoDatabase mangoDatabase;// = mangoClient.getDatabase("dislinkt");
	// private MongoCollection<Document> mangoCollection;// = mangoDatabase.getCollection("users");

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
		mangoCollection.find(new Document("userId", request.getId())).first();
		responseObserver.onCompleted();
	}

}
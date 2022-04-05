package dislinkt.userservice.Controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dislinkt.userclient.UserDTO;
import dislinkt.userclient.UserServiceFeignClient;

@RestController
public class UserController implements UserServiceFeignClient {

	@Override
	public String home() {
		return "Hello from User Service";
	}

	@Override
	public ResponseEntity<UserDTO> userLogin(RequestEntity<UserDTO> user1) {
		return null;
	}

	@Override
	public ResponseEntity<UserDTO> userRegister(RequestEntity<UserDTO> user1) {
		return null;
	}
}

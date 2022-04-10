package dislinkt.userservice.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dislinkt.userclient.UserDTO;
import dislinkt.userclient.UserServiceFeignClient;
import dislinkt.userservice.Entity.User;
import dislinkt.userservice.Mapper.UserMapper;
import dislinkt.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController implements UserServiceFeignClient {

	private final UserService userService;

	private final UserMapper userMapper;

	@GetMapping(value = "/actuator/info")
	@Override
	public String home() {
		return "Hello from User Service";
	}

	@Override
	public ResponseEntity<ArrayList<UserDTO>> findByName(@RequestBody Map<String, String> json) {
		String firstName = json.get("firstName");
		String lastName = json.get("lastName");

		System.out.println(firstName);
		System.out.println(lastName);

		ArrayList<UserDTO> users = userService.findByName(firstName, lastName);
		if (users == null) {
			System.out.println("FindByName: No users found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ArrayList<UserDTO>>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ArrayList<UserDTO>> findByFirstName(@RequestBody String firstName) {
		ArrayList<UserDTO> users = userService.findByFirstName(firstName);
		if (users.isEmpty()) {
			System.out.println("FindByFirstName: No users found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ArrayList<UserDTO>> findByLastName(@RequestBody String lastName) {
		ArrayList<UserDTO> users = userService.findByLastName(lastName);
		if (users.isEmpty()) {
			System.out.println("FindByLastName: No users found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> findByUsername(@RequestBody String username) {
		UserDTO user = userService.findByUsername(username);
		if (user == null) {
			System.out.println("FindByUsername: No user found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ArrayList<UserDTO>> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> checkUsername(@RequestBody String username) {
		return new ResponseEntity<Boolean>(userService.checkUsername(username), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> updateUsername(@RequestBody Map<String, String> json) {
		String oldUsername = json.get("oldUsername");
		String newUsername = json.get("newUsername");
		User allegedUser = userService.updateUsername(oldUsername, newUsername);

		if (allegedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		UserDTO userDTO = userMapper.entityToDto(allegedUser);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> userUpdate(@RequestBody UserDTO incomingUser) {
		User allegedUser = userService.update(incomingUser);

		if (allegedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		UserDTO userDTO = userMapper.entityToDto(allegedUser);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> userLogin(@RequestBody UserDTO incomingUser) {
		User allegedUser = userService.login(incomingUser);

		if (allegedUser == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		UserDTO userDTO = userMapper.entityToDto(allegedUser);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> userRegister(@RequestBody UserDTO incomingUser) {
		User newUser = userService.register(incomingUser);

		if (newUser == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		UserDTO userDTO = userMapper.entityToDto(newUser);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}

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
	public ResponseEntity<Boolean> approveFollow(@RequestBody Map<String, String> userIds) {
		boolean retVal = userService.approveFollow(userIds.get("userId"), userIds.get("followerUserId"));
		if (!retVal) {
			return new ResponseEntity<>(retVal, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@Override	
	public ResponseEntity<Boolean> followUser(@RequestBody Map<String, String> userIds) {
		boolean retVal = userService.followUser(userIds.get("userId"), userIds.get("toFollowUserId"));
		if (!retVal) {
			return new ResponseEntity<>(retVal, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> viewUser(@RequestBody Map<String, String> userIds) {
		UserDTO userDTO = userService.viewUser(userIds.get("userId"), userIds.get("toViewUserId"));
		if (userDTO == null) {
			return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Override 
	public ResponseEntity<ArrayList<UserDTO>> find(@RequestBody Map<String, String> searchTerm) {
		ArrayList<UserDTO> users = userService.find(searchTerm.get("firstName"), searchTerm.get("lastName"));
		if (searchTerm.isEmpty()) {
			System.out.println("Find - No search term");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else if (users == null) {
			System.out.println("No users found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ArrayList<UserDTO>>(users, HttpStatus.OK);
	}

	// e al ovih narednih 5 funkcija se samo ne koristi btw, koristi se samo jedna i to ova iznad
	@Override
	public ResponseEntity<ArrayList<UserDTO>> findByName(@RequestBody Map<String, String> searchTerm) {
		ArrayList<UserDTO> users = userService.findByName(searchTerm.get("lastName"), searchTerm.get("lastName"));
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

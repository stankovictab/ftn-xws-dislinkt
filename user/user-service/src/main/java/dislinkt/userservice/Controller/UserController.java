package dislinkt.userservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserController implements UserServiceFeignClient {

	private final UserService userService;

	private final UserMapper userMapper;

	@GetMapping(value = "/actuator/info")
	@Override
	public String home() {
		return "Hello from User Service";
	}

	@Override
	public ResponseEntity<UserDTO> userLogin(@RequestBody UserDTO user1) {
		User allegedUser = userService.login(user1);

		if (allegedUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
		
		UserDTO userDTO = userMapper.entityToDto(allegedUser);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> userRegister(@RequestBody UserDTO user1) {
		User newUser = userService.register(user1);

		if (newUser == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		UserDTO userDTO = userMapper.entityToDto(newUser);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}

package dislinkt.userservice.Controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dislinkt.userservice.Entity.User;

@RestController
public class UserController {
    

    @PostMapping("/user/login")
    public ResponseEntity<User> userLogin(RequestEntity<User> user1) {
        
        




        return null;
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> userRegister(RequestEntity<User> user1) throws NoSuchAlgorithmException, InvalidKeySpecException {
        
        


        
        return null;
    }
}

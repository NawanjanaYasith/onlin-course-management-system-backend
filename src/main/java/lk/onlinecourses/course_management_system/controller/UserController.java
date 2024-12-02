package lk.onlinecourses.course_management_system.controller;

import lk.onlinecourses.course_management_system.dto.UserDTO;
import lk.onlinecourses.course_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user){
        UserDTO userDTO=userService.userRegister(user);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO user){
        String userResponse=userService.userLogin(user);

        if (userResponse == null) {
            return new ResponseEntity<>("Bad Credentials!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}

package lk.onlinecourses.course_management_system.service.impl;

import lk.onlinecourses.course_management_system.dto.UserDTO;
import lk.onlinecourses.course_management_system.entity.User;
import lk.onlinecourses.course_management_system.repo.UserRepo;
import lk.onlinecourses.course_management_system.service.UserService;
import lk.onlinecourses.course_management_system.util.JwtAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtAuthenticator jwtAuthenticator;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, JwtAuthenticator jwtAuthenticator) {
        this.userRepo = userRepo;
        this.jwtAuthenticator = jwtAuthenticator;
    }

    @Override
    public UserDTO userRegister(UserDTO user) {
        String encodedString = Base64.getEncoder().encodeToString(user.getPassword().getBytes());

        User save=userRepo.save(new User(user.getId(),user.getUserName(), encodedString, user.getRole()));
        return new UserDTO(save.getId(),save.getUserName(),save.getPassword(),save.getRole());
    }

    @Override
    public String userLogin(UserDTO user) {
        User userByEmail=userRepo.findUserByUserName(user.getUserName());

        if (userByEmail!=null){
            //existing user enocoded password decoded
            byte[] decodedBytes = Base64.getDecoder().decode(userByEmail.getPassword());
            String decodedString = new String(decodedBytes);

            if (decodedString.equals(user.getPassword())) {
                return jwtAuthenticator.generateJwtToken(userByEmail);
            }
        }
        return null;
    }
}

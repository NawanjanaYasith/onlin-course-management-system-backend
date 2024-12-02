package lk.onlinecourses.course_management_system.service;

import lk.onlinecourses.course_management_system.dto.UserDTO;

public interface UserService {
    UserDTO userRegister(UserDTO user);

    String userLogin(UserDTO user);
}

package lk.onlinecourses.course_management_system.repo;

import lk.onlinecourses.course_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findUserByUserName(String userName);

}

package lk.onlinecourses.course_management_system.repo;

import lk.onlinecourses.course_management_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Integer> {
}

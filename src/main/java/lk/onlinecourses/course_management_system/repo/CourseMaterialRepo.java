package lk.onlinecourses.course_management_system.repo;

import lk.onlinecourses.course_management_system.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMaterialRepo extends JpaRepository<CourseMaterial,Integer> {
}

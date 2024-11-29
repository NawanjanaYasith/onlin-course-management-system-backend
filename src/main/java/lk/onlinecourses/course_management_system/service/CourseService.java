package lk.onlinecourses.course_management_system.service;

import lk.onlinecourses.course_management_system.dto.CourseDTOWithCourseMaterial;
import lk.onlinecourses.course_management_system.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDTOWithCourseMaterial saveCourseWithMaterials(CourseDTOWithCourseMaterial course);

    boolean deleteCourse(Integer id);
    List<CourseDTOWithCourseMaterial> getAllCourse();
}

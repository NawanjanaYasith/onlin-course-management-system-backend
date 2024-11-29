package lk.onlinecourses.course_management_system.service;

import lk.onlinecourses.course_management_system.dto.CourseDTOWithCourseMaterial;

public interface CourseService {
    CourseDTOWithCourseMaterial saveCourseWithMaterials(CourseDTOWithCourseMaterial course);

    boolean deleteCourse(Integer id);
}

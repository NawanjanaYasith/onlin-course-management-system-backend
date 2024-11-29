package lk.onlinecourses.course_management_system.service;

import lk.onlinecourses.course_management_system.dto.CourseDTOWithCourseMaterial;
import lk.onlinecourses.course_management_system.dto.CourseMaterialDTOWithCourse;

public interface CourseMatericalService {
    CourseMaterialDTOWithCourse saveCourseMaterials(CourseMaterialDTOWithCourse dto);

}

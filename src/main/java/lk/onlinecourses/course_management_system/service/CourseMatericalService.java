package lk.onlinecourses.course_management_system.service;

import lk.onlinecourses.course_management_system.dto.CourseDTOWithCourseMaterial;
import lk.onlinecourses.course_management_system.dto.CourseMaterialDTOWithCourse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseMatericalService {
//    CourseMaterialDTOWithCourse saveCourseMaterials(CourseMaterialDTOWithCourse dto);
    CourseMaterialDTOWithCourse saveCourseMaterials(MultipartFile file, Integer id) throws IOException;

}

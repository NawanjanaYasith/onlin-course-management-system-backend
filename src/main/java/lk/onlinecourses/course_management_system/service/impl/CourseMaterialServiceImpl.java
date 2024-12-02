package lk.onlinecourses.course_management_system.service.impl;

import lk.onlinecourses.course_management_system.dto.CourseMaterialDTOWithCourse;
import lk.onlinecourses.course_management_system.entity.Course;
import lk.onlinecourses.course_management_system.entity.CourseMaterial;
import lk.onlinecourses.course_management_system.repo.CourseMaterialRepo;
import lk.onlinecourses.course_management_system.repo.CourseRepo;
import lk.onlinecourses.course_management_system.service.CourseMatericalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CourseMaterialServiceImpl implements CourseMatericalService {

    private final CourseMaterialRepo courseMaterialRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public CourseMaterialServiceImpl(CourseMaterialRepo courseMaterialRepo, CourseRepo courseRepo) {
        this.courseMaterialRepo = courseMaterialRepo;
        this.courseRepo = courseRepo;
    }


    @Override
    public CourseMaterialDTOWithCourse saveCourseMaterials(MultipartFile file, Integer id) throws IOException {
        String fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get("uploads/",fileName);
        Files.createDirectories(uploadPath.getParent());
        Files.write(uploadPath,file.getBytes());


        // Retrieve the course entity by courseId
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course with ID " + id + " not found"));
        CourseMaterial courseMaterial =new CourseMaterial();
        courseMaterial.setFileName(fileName);
        courseMaterial.setFileUrl(uploadPath.toString());
        courseMaterial.setCourse(course); // Set the associated course
        CourseMaterial save = courseMaterialRepo.save(courseMaterial);

        return new CourseMaterialDTOWithCourse(save.getId(),save.getFileName(),save.getFileUrl(),save.getCourse().getId());
    }
}

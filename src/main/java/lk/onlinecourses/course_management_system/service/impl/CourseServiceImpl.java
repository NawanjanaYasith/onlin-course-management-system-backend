package lk.onlinecourses.course_management_system.service.impl;

import lk.onlinecourses.course_management_system.dto.CourseDTOWithCourseMaterial;
import lk.onlinecourses.course_management_system.dto.CourseDto;
import lk.onlinecourses.course_management_system.dto.CourseMaterialDto;
import lk.onlinecourses.course_management_system.entity.Course;
import lk.onlinecourses.course_management_system.entity.CourseMaterial;
import lk.onlinecourses.course_management_system.repo.CourseRepo;
import lk.onlinecourses.course_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {


    private final CourseRepo courseRepo;

    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseDTOWithCourseMaterial saveCourseWithMaterials(CourseDTOWithCourseMaterial course) {
        Course entity=new Course(course.getId(), course.getTitle(), course.getDescription());

        //convert incoming CourseMaterialDto to CourseMaterial entity list
        List<CourseMaterial> materials = new ArrayList<>();
        for (CourseMaterialDto dto : course.getCourseMaterialDto()) {
            materials.add(new CourseMaterial(dto.getId(), dto.getFileName(), dto.getFileUrl(),entity));
        }

        entity.setCourseMaterials(materials);

        //course save with above material list
        Course save = courseRepo.save(entity);

        //convert CourseMaterial entities to CourseMaterialDto
        List<CourseMaterialDto> dtoMaterials = new ArrayList<>();
        for (CourseMaterial material : save.getCourseMaterials()) {
            dtoMaterials.add(new CourseMaterialDto(material.getId(), material.getFileName(), material.getFileUrl()));
        }

        return new CourseDTOWithCourseMaterial(save.getId(), save.getTitle(), save.getDescription(),dtoMaterials );
    }

    @Override
    public boolean deleteCourse(Integer id) {
        if (courseRepo.existsById(id)) {
            courseRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CourseDTOWithCourseMaterial> getAllCourse() {
        List<Course> all = courseRepo.findAll(); // Fetch all courses from the repository

        List<CourseDTOWithCourseMaterial> courseDTOWithCourseMaterials = new ArrayList<>();

        for (Course course : all) {
            // Convert CourseMaterial entities to CourseMaterialDto objects
            List<CourseMaterialDto> courseMaterialDtos = course.getCourseMaterials().stream()
                    .map(courseMaterial -> new CourseMaterialDto(
                            courseMaterial.getId(),
                            courseMaterial.getFileName(),
                            courseMaterial.getFileUrl()
                    ))
                    .collect(Collectors.toList());

            // Create CourseDTOWithCourseMaterial using the data from Course and CourseMaterial
            courseDTOWithCourseMaterials.add(new CourseDTOWithCourseMaterial(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    courseMaterialDtos
            ));
        }

        return courseDTOWithCourseMaterials;
    }

}

package lk.onlinecourses.course_management_system.service.impl;

import lk.onlinecourses.course_management_system.dto.CourseMaterialDTOWithCourse;
import lk.onlinecourses.course_management_system.entity.Course;
import lk.onlinecourses.course_management_system.entity.CourseMaterial;
import lk.onlinecourses.course_management_system.repo.CourseMaterialRepo;
import lk.onlinecourses.course_management_system.service.CourseMatericalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialServiceImpl implements CourseMatericalService {

    private final CourseMaterialRepo courseMaterialRepo;

    @Autowired
    public CourseMaterialServiceImpl(CourseMaterialRepo courseMaterialRepo) {
        this.courseMaterialRepo = courseMaterialRepo;
    }


    @Override
    public CourseMaterialDTOWithCourse saveCourseMaterials(CourseMaterialDTOWithCourse dto) {
        CourseMaterial save = courseMaterialRepo.save(new CourseMaterial(dto.getId(), dto.getFileName(), dto.getFileUrl(), new Course(dto.getCourseID())));
        return new CourseMaterialDTOWithCourse(save.getId(),save.getFileName(),save.getFileUrl(),save.getCourse().getId());
    }
}

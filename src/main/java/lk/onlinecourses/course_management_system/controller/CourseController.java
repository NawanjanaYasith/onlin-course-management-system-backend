package lk.onlinecourses.course_management_system.controller;

import lk.onlinecourses.course_management_system.dto.CourseDTOWithCourseMaterial;
import lk.onlinecourses.course_management_system.dto.CourseDto;
import lk.onlinecourses.course_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course_save")
    public ResponseEntity<CourseDTOWithCourseMaterial> courseSave(@RequestBody CourseDTOWithCourseMaterial courseDTOWithCourseMaterial) {
        CourseDTOWithCourseMaterial courseDTO = courseService.saveCourseWithMaterials(courseDTOWithCourseMaterial);
        return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id){
        boolean b = courseService.deleteCourse(id);
        if(b){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot delete", HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/all_courses")
    public ResponseEntity<List<CourseDTOWithCourseMaterial>> getVehicles() {
        List<CourseDTOWithCourseMaterial> allCourse = courseService.getAllCourse();
        return new ResponseEntity<>(allCourse, HttpStatus.OK);

    }
    @GetMapping("/get_course/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Integer id) {
        System.out.println(id);
        List<CourseDTOWithCourseMaterial> byId = courseService.getById(id);
        if (byId == null) {
            return new ResponseEntity<>("No course exist",HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
        return new ResponseEntity<>(byId, HttpStatus.OK);

    }
}

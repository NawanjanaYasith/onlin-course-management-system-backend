package lk.onlinecourses.course_management_system.controller;

import lk.onlinecourses.course_management_system.dto.CourseMaterialDTOWithCourse;
import lk.onlinecourses.course_management_system.service.CourseMatericalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("course-material")
public class CourseMaterialController {

    private final CourseMatericalService courseMaterialService ;

    @Autowired
    public CourseMaterialController(CourseMatericalService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

//    @PostMapping("cousre-material-save")
//    public ResponseEntity<CourseMaterialDTOWithCourse> saveCourseMaterial(@RequestBody CourseMaterialDTOWithCourse courseMaterialDTOWithCourse){
//        CourseMaterialDTOWithCourse materialDto=courseMaterialService.saveCourseMaterials(courseMaterialDTOWithCourse);
//        return new ResponseEntity<>(materialDto, HttpStatus.CREATED);
//
//    }
    @PostMapping("/upload/{id}")
    public ResponseEntity<CourseMaterialDTOWithCourse> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer id) throws IOException {
        CourseMaterialDTOWithCourse materialDto = courseMaterialService.saveCourseMaterials(file, id);
        return new ResponseEntity<>(materialDto, HttpStatus.CREATED);
    }
}



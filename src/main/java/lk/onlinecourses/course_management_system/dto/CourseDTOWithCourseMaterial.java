package lk.onlinecourses.course_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTOWithCourseMaterial {
    private Integer id;
    private String title;
    private String description;
    private List<CourseMaterialDto> courseMaterialDto;
}

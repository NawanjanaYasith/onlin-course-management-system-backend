package lk.onlinecourses.course_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialDto {
    private Integer id;
    private String fileName;
    private String fileUrl;
}

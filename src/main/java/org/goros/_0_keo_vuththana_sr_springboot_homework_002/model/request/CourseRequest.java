package org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Instructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String courseName;
    private String description;
    private Long instructorId;
}

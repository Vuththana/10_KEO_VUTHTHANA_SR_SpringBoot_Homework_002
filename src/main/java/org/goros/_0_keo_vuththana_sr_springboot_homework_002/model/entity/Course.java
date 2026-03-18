package org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}

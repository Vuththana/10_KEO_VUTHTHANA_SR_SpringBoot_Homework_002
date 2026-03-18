package org.goros._0_keo_vuththana_sr_springboot_homework_002.service;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Course;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer page, Integer size);
    Course getCourseById(Integer courseId);
    Course saveCourse(CourseRequest request);
    int deleteCourseById(Integer courseId);
    int updateCourseById(Integer courseId, CourseRequest request);
}

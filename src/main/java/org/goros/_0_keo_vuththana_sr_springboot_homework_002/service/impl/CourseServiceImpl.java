package org.goros._0_keo_vuththana_sr_springboot_homework_002.service.impl;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Course;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.CourseRequest;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.CourseRepository;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return courseRepository.getAllCourses(offset, size);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public Course saveCourse(CourseRequest request) {
        return courseRepository.saveCourse(request);
    }

    @Override
    public int deleteCourseById(Integer courseId) {
        return courseRepository.deletedCourseById(courseId);
    }

    @Override
    public int updateCourseById(Integer courseId, CourseRequest request) {
        return courseRepository.updateCourseById(courseId, request);
    }
}

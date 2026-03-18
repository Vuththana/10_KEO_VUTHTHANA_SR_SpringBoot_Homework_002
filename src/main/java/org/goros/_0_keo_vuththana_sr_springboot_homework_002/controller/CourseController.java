package org.goros._0_keo_vuththana_sr_springboot_homework_002.controller;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Course;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.CourseRequest;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.response.ApiResponse;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.response.ApiResponseVoid;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.service.CourseService;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses/")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Course> courses = courseService.getAllCourses(page, size);
        ApiResponse<List<Course>> response = ResponseUtil.success(HttpStatus.OK, "Course fetched successfully", courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        ApiResponse<Course> response = course != null ? ResponseUtil.success(HttpStatus.OK, "Course fetched successfully", course) : ResponseUtil.error(HttpStatus.NOT_FOUND, "Course not found with that ID");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> saveCourse(@RequestBody CourseRequest request) {
        Course createdCourse = courseService.saveCourse(request);
        ApiResponse<Course> response = ResponseUtil.success(HttpStatus.OK, "Course created successfully", createdCourse);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("{course-id}")
    public ResponseEntity<ApiResponseVoid> deleteCourseById(@PathVariable("course-id") Integer courseId) {
        int deletedCourse = courseService.deleteCourseById(courseId);
        ApiResponseVoid response = deletedCourse != 0 ? ResponseUtil.successVoid(HttpStatus.OK, "Course deleted successfully") : ResponseUtil.errorVoid(HttpStatus.NOT_FOUND, "No course found with that ID");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("{course-id}")
    public ResponseEntity<ApiResponseVoid> updateCourseById(@PathVariable("course-id") Integer courseId, @RequestBody CourseRequest request) {
        int updatedCourse = courseService.updateCourseById(courseId, request);
        ApiResponseVoid response = updatedCourse != 0 ? ResponseUtil.successVoid(HttpStatus.OK, "Course updated successfully") : ResponseUtil.errorVoid(HttpStatus.NOT_FOUND, "No course found with that ID");

        return ResponseEntity.status(response.getStatus()).body(response);
    }


}

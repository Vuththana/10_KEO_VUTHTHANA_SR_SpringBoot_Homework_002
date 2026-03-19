package org.goros._0_keo_vuththana_sr_springboot_homework_002.controller;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Student;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.StudentRequest;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.response.ApiResponse;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.response.ApiResponseVoid;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.service.StudentService;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students/")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Student> students = studentService.getAllStudents(page, size);
        ApiResponse<List<Student>> response = ResponseUtil.success(HttpStatus.OK, "Student fetched successfully", students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Integer studentId) {
        Student student = studentService.getStudentById(studentId);
        ApiResponse<Student> response = student != null ? ResponseUtil.success(HttpStatus.OK, "Student fetched successfully", student) : ResponseUtil.error(HttpStatus.NOT_FOUND, "No student found with that ID");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> saveStudent(@RequestBody StudentRequest request) {
        Student createdStudent = studentService.saveStudent(request);
        ApiResponse<Student> response = createdStudent != null ? ResponseUtil.success(HttpStatus.CREATED, "Student created successfully", createdStudent) : ResponseUtil.error(HttpStatus.NOT_FOUND, "Course ID not found.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("{student-id}")
    public ResponseEntity<ApiResponseVoid> deleteStudentById(@PathVariable("student-id") Integer studentId) {
        int deletedStudent = studentService.deleteStudentById(studentId);
        ApiResponseVoid response = deletedStudent != 0  ?  ResponseUtil.successVoid(HttpStatus.OK, "Deleted student successfully" ) : ResponseUtil.errorVoid(HttpStatus.NOT_FOUND, "No student found with that ID " + studentId) ;

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("{student-id}")
    public ResponseEntity<ApiResponseVoid> updateStudentById(@PathVariable("student-id") Integer studentId, @RequestBody StudentRequest request) {
        int updatedStudent = studentService.updateStudentById(studentId, request);
        ApiResponseVoid response = updatedStudent != 0 ? ResponseUtil.successVoid(HttpStatus.OK, "Updated student successfully") : ResponseUtil.errorVoid(HttpStatus.NOT_FOUND, "No student found with that ID") ;

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

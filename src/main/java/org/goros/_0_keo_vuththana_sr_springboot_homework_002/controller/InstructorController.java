package org.goros._0_keo_vuththana_sr_springboot_homework_002.controller;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Instructor;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.InstructorRequest;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.response.ApiResponse;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.response.ApiResponseVoid;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.service.InstructorService;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors/")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Instructor> instructors =  instructorService.getAllInstructors(page, size);
        ApiResponse<List<Instructor>> response = ResponseUtil.success(HttpStatus.OK, "Instructor fetched successfully", instructors);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Integer instructorId) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        ApiResponse<Instructor> response = instructor != null ? ResponseUtil.success(HttpStatus.OK, "Instructor fetched successfully", instructor) : ResponseUtil.error(HttpStatus.NOT_FOUND, "No Instructor found with the id: " + instructorId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> saveInstructor(@RequestBody InstructorRequest request) {
        Instructor createdInstructor = instructorService.saveInstructor(request);
        ApiResponse<Instructor> response = ResponseUtil.success(HttpStatus.CREATED, "Created Instructor Successfully", createdInstructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{instructor-id}")
    public ResponseEntity<ApiResponseVoid> deleteInstructorById(@PathVariable("instructor-id") Integer instructorId) {
        int deletedInstructor = instructorService.deleteInstructorById(instructorId);
        ApiResponseVoid response = deletedInstructor != 0 ? ResponseUtil.successVoid(HttpStatus.OK, "Instructor Deleted Successfully") : ResponseUtil.errorVoid(HttpStatus.NOT_FOUND, "No instructor found with that ID");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("{instructor-id}")
    public ResponseEntity<ApiResponseVoid> updateInstructorById(@PathVariable("instructor-id") Integer instructorId, InstructorRequest request) {
        int updatedInstructor = instructorService.updateInstructorById(instructorId, request);
        ApiResponseVoid response = updatedInstructor != 0 ? ResponseUtil.successVoid(HttpStatus.OK, "Instructor updated successfully") : ResponseUtil.errorVoid(HttpStatus.NOT_FOUND, "No instructor found with that ID");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

package org.goros._0_keo_vuththana_sr_springboot_homework_002.service;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Student;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer page, Integer size);
    Student getStudentById(Integer studentId);
    Student saveStudent(StudentRequest request);
    int deleteStudentById(Integer studentId);
    int updateStudentById(Integer studentId, StudentRequest request);
}

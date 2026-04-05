package org.goros._0_keo_vuththana_sr_springboot_homework_002.service.impl;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Course;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Student;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.StudentRequest;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.CourseRepository;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.StudentCourseRepository;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.StudentRepository;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return studentRepository.getAllStudents(offset, size);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Student saveStudent(StudentRequest request) {
        Student student = studentRepository.saveStudent(request);
        for(Long courseId : request.getCourseId()) {
            Course course = courseRepository.getCourseById(Math.toIntExact(courseId));
            if(course == null) {
                return null;
            }
            studentCourseRepository.insertStudentCourse(student.getStudentId(), courseId);
        }
        return studentRepository.getStudentById(Math.toIntExact(student.getStudentId()));
    }

    @Override
    public int deleteStudentById(Integer studentId) {
        return studentRepository.deleteStudentById(studentId);
    }

    @Override
    public int updateStudentById(Integer studentId, StudentRequest request) {
        Student existingStudent = studentRepository.getStudentById(studentId);
        if(existingStudent == null) {
            return 0;
        }
        int rowsAffected = studentRepository.updateStudentById(studentId, request);
        studentCourseRepository.deleteStudentCourse(Long.valueOf(studentId));
        for(Long courseId : request.getCourseId()) {
            Course course = courseRepository.getCourseById(Math.toIntExact(courseId));
            if(course != null) {
                studentCourseRepository.insertStudentCourse(Long.valueOf(studentId),courseId);
            }
        }
        return rowsAffected;
    }
}

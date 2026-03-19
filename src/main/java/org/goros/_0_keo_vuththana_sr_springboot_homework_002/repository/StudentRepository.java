package org.goros._0_keo_vuththana_sr_springboot_homework_002.repository;

import org.apache.ibatis.annotations.*;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Student;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.StudentRequest;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id", many = @Many(select = "org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.StudentCourseRepository.getCoursesByStudentId"))
    })
    @Select("""
    SELECT * FROM students LIMIT #{size} OFFSET #{offset}
    """)
    List<Student> getAllStudents(Integer offset, Integer size);

    @ResultMap("studentMapper")
    @Select("""
    SELECT * FROM students WHERE student_id = #{studentId}
    """)
    Student getStudentById(Integer studentId);

    @ResultMap("studentMapper")
    @Select("""
    INSERT INTO students(student_name, email, phone_number) VALUES (#{studentName}, #{email}, #{phoneNumber}) RETURNING *;
    """)
    Student saveStudent(StudentRequest request);

    @ResultMap("studentMapper")
    @Delete("""
    DELETE FROM students WHERE student_id = #{studentId};
    """)
    int deleteStudentById(Integer studentId);

    @ResultMap("studentMapper")
    @Update("""
    UPDATE students SET student_name=#{req.studentName}, email=#{req.email}, phone_number=#{req.phoneNumber} WHERE student_id = #{studentId};
    """)
    int updateStudentById(Integer studentId, @Param("req") StudentRequest request);
}

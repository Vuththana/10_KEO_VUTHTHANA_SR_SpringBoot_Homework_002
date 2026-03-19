package org.goros._0_keo_vuththana_sr_springboot_homework_002.repository;

import org.apache.ibatis.annotations.*;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Course;

import java.util.List;

@Mapper
public interface StudentCourseRepository {

    @Results(id = "studentCategoryMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", many = @Many(select = "org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.InstructorRepository.getInstructorById"))
    })
    @Select("""
    SELECT * FROM student_course sc INNER JOIN courses c 
        ON sc.course_id = c.course_id WHERE student_id = #{studdentId};
    """)
    List<Course> getCoursesByStudentId(Long studentId);

    @ResultMap("studentCategoryMapper")
    @Insert("""
    INSERT INTO student_course VALUES (#{studentId}, #{courseId})
    """)
    void insertStudentCourse(Long studentId, Long courseId);

    @Delete("""
    DELETE FROM student_course WHERE student_id = #{studentId}
    """)
    void deleteStudentCourse(Long studentId);
}

package org.goros._0_keo_vuththana_sr_springboot_homework_002.repository;

import org.apache.ibatis.annotations.*;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Course;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.CourseRequest;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.InstructorRepository.getInstructorById")),
    })
    @Select("""
    SELECT * FROM courses LIMIT #{size} OFFSET #{offset}
    """)
    List<Course> getAllCourses(Integer offset, Integer size);

    @Select("""
    SELECT * FROM courses WHERE course_id = #{courseId}
    """)
    Course getCourseById(Integer courseId);

    @Select("""
    INSERT INTO courses (course_name, description, instructor_id) VALUES (#{courseName}, #{description}, #{instructorId})
    """)
    Course saveCourse(CourseRequest request);

    @Delete("""
    DELETE FROM courses WHERE course_id = #{courseId}
    """)
    int deletedCourseById(Integer courseId);

    @Update("""
    UPDATE courses SET course_name = #{req.courseName}, description = #{req.description}, instructor_id = #{req.instructorId} WHERE course_id = #{courseId}
    """)
    int updateCourseById(Integer courseId, @Param("req")CourseRequest request);
}

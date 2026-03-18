package org.goros._0_keo_vuththana_sr_springboot_homework_002.repository;

import org.apache.ibatis.annotations.*;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Instructor;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.InstructorRequest;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
    })
    @Select("""
    SELECT * FROM instructors LIMIT #{size} OFFSET #{offset}
    """)
    List<Instructor> getAllInstructors(Integer offset, Integer size);

    @ResultMap("instructorMapper")
    @Select("""
    SELECT * FROM instructors WHERE instructor_id = #{instructorId}
    """)
    Instructor getInstructorById(Integer instructorId);

    @ResultMap("instructorMapper")
    @Select("""
    INSERT INTO instructors(instructor_name, email) VALUES (#{instructorName}, #{email})
    """)
    Instructor saveInstructor(InstructorRequest request);

    @Delete("""
    DELETE FROM instructor WHERE instructor_id = #{instructorId};
    """)
    int deleteInstructorById(Integer instructorId);

    @Update("""
    UPDATE instructors SET instructor_name = #{req.instructorName}, email = #{req.email} WHERE instructor_id = #{instructorId};
    """)
    int updateInstructorById(Integer instructorId, @Param("req") InstructorRequest request);
}

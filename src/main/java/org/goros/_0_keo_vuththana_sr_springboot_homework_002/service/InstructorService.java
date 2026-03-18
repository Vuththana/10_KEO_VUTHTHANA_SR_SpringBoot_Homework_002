package org.goros._0_keo_vuththana_sr_springboot_homework_002.service;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Instructor;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer page, Integer size);
    Instructor getInstructorById(Integer instructorId);
    Instructor saveInstructor(InstructorRequest request);
    int deleteInstructorById(Integer instructorId);
    int updateInstructorById(Integer instructorId, InstructorRequest request);
}

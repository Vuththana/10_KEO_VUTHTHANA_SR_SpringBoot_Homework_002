package org.goros._0_keo_vuththana_sr_springboot_homework_002.service.impl;

import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.entity.Instructor;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.model.request.InstructorRequest;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.repository.InstructorRepository;
import org.goros._0_keo_vuththana_sr_springboot_homework_002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return instructorRepository.getAllInstructors(offset, size);
    }

    @Override
    public Instructor getInstructorById(Integer instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public Instructor saveInstructor(InstructorRequest request) {
        return instructorRepository.saveInstructor(request);
    }

    @Override
    public int deleteInstructorById(Integer instructorId) {
        return instructorRepository.deleteInstructorById(instructorId);
    }

    @Override
    public int updateInstructorById(Integer instructorId, InstructorRequest request) {
        return instructorRepository.updateInstructorById(instructorId, request);
    }
}

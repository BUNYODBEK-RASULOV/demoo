package com.example.demo.service;

import com.example.demo.entity.FacultyEntity;
import com.example.demo.model.receive.FacultyReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService implements BaseService<FacultyReceiveDTO> {
    private final FacultyRepository repository;
    private final ResponseUtils responseUtils;
    private final UniversityService universityService;
    private final UniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    public boolean check(String name){
        return repository.findByName(name).isPresent();
    }

    public boolean check(long id){
        return repository.findById(id).isPresent();
    }

    @Override
    public ApiResponse add(FacultyReceiveDTO facultyReceiveDTO) {
        if (check(facultyReceiveDTO.getName()))
            return responseUtils.FACULTY_EXISTS;
        if (!universityService.check(facultyReceiveDTO.getUniversityId()))
            return responseUtils.UNIVERSITY_NOT_FOUND;

        FacultyEntity faculty=modelMapper.map(facultyReceiveDTO,FacultyEntity.class);
        faculty.setUniversity(universityRepository.findById(facultyReceiveDTO.getUniversityId()).get());
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(faculty));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        if (!check(id))
            return responseUtils.FACULTY_NOT_FOUND;
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.findById(id).get());
        return success;
    }

    @Override
    public ApiResponse list() {
        List<FacultyEntity> all = repository.findAll();
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(all);
        return success;
    }

    @Override
    public ApiResponse update(long id, FacultyReceiveDTO facultyReceiveDTO) {
        if (!check(id))
            return responseUtils.FACULTY_NOT_FOUND;
        FacultyEntity faculty = repository.findById(id).get();

        if (facultyReceiveDTO.getName()!=null)
            faculty.setName(facultyReceiveDTO.getName());

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(faculty));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        if (!check(id))
            return responseUtils.FACULTY_NOT_FOUND;
        repository.deleteById(id);
        return responseUtils.SUCCESS;
    }

    public ApiResponse groupAndStudentCountByFacultyId(long id){
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.groupAndStudentCountByFacultyId(id));
        return success;
    }
}

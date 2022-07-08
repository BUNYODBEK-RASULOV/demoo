package com.example.demo.service;


import com.example.demo.entity.GroupEntity;
import com.example.demo.model.receive.GroupReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements BaseService<GroupReceiveDTO> {
    private final GroupRepository repository;
    private final ModelMapper modelMapper;
    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;
    private final ResponseUtils responseUtils;


    public boolean check(String name){
        return repository.findByName(name).isPresent();
    }

    public boolean check(long id){
        return repository.findById(id).isPresent();
    }


    @Override
    public ApiResponse add(GroupReceiveDTO groupReceiveDTO) {
        if (check(groupReceiveDTO.getName()))
        return responseUtils.GROUP_EXISTS;
        GroupEntity group=modelMapper.map(groupReceiveDTO,GroupEntity.class);
        if (!facultyService.check(groupReceiveDTO.getFacultyId()))
            return responseUtils.FACULTY_NOT_FOUND;
        group.setFaculty(facultyRepository.findById(groupReceiveDTO.getFacultyId()).get());
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(group));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        Optional<GroupEntity> group = repository.findById(id);
        if (group.isEmpty())
            return responseUtils.GROUP_NOT_FOUND;
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(group.get());
        return success;
    }

    @Override
    public ApiResponse list() {
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.findAll());
        return success;
    }

    @Override
    public ApiResponse update(long id, GroupReceiveDTO groupReceiveDTO) {
        Optional<GroupEntity> optional = repository.findById(id);
        if (optional.isEmpty())
            return responseUtils.GROUP_NOT_FOUND;

        GroupEntity group=optional.get();
        if (groupReceiveDTO.getName()!=null)
            group.setName(groupReceiveDTO.getName());
        if (groupReceiveDTO.getYear()!=0L)
            group.setYear(groupReceiveDTO.getYear());

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(group);
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        if (!check(id))
            return responseUtils.GROUP_NOT_FOUND;
        repository.deleteById(id);
        return responseUtils.SUCCESS;
    }

    public ApiResponse markAverageValueStudent(long id){
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.markAverageValueStudent(id));
        return success;
    }
}

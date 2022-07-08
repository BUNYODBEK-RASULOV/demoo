package com.example.demo.service;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.model.receive.SubjectReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<SubjectReceiveDTO> {

    private final ResponseUtils responseUtils;
    private final SubjectRepository repository;
    private final ModelMapper modelMapper;

    public boolean check(String name){
        return repository.findByName(name).isPresent();
    }

    public boolean check(long id){
        return repository.findById(id).isPresent();
    }

    @Override
    public ApiResponse add(SubjectReceiveDTO subjectReceiveDTO) {
        if (check(subjectReceiveDTO.getName()))
            return responseUtils.SUBJECT_EXISTS;
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(modelMapper.map(subjectReceiveDTO,SubjectEntity.class)));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        if (!check(id))
            return responseUtils.SUBJECT_NOT_FOUND;
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.findById(id).get());
        return success;
    }

    @Override
    public ApiResponse list() {
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.findAll());
        return success;
    }

    @Override
    public ApiResponse update(long id, SubjectReceiveDTO subjectReceiveDTO) {
        if (!check(id))
            return responseUtils.SUBJECT_NOT_FOUND;
        SubjectEntity subject=repository.findById(id).get();
        if (subjectReceiveDTO.getName()!=null)
            subject.setName(subjectReceiveDTO.getName());
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(subject));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        if (!check(id))
            return responseUtils.SUBJECT_NOT_FOUND;
        repository.deleteById(id);
        return responseUtils.SUCCESS;
    }
}

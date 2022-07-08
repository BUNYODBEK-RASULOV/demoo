package com.example.demo.service;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.model.receive.StudentFacultyGroupDTO;
import com.example.demo.model.receive.StudentReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<StudentReceiveDTO>{
    private final StudentRepository repository;
    private final ResponseUtils responseUtils;
    private final ModelMapper modelMapper;
    private  final GroupService groupService;
    private final GroupRepository groupRepository;

    public boolean check(String name){
        return repository.findByName(name).isPresent();
    }

    public boolean check(long id){
        return repository.findById(id).isPresent();
    }

    @Override
    public ApiResponse add(StudentReceiveDTO studentReceiveDTO) {
        if (check(studentReceiveDTO.getName()))
            return responseUtils.STUDENT_EXISTS;
        StudentEntity student=modelMapper.map(studentReceiveDTO,StudentEntity.class);
        if (!groupService.check(studentReceiveDTO.getGroupId()))
            return responseUtils.GROUP_NOT_FOUND;
        student.setGroup(groupRepository.findById(studentReceiveDTO.getGroupId()).get());

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(student));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        if (!check(id))
            return responseUtils.STUDENT_NOT_FOUND;
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
    public ApiResponse update(long id, StudentReceiveDTO studentReceiveDTO) {
        if (!check(id))
            return responseUtils.STUDENT_NOT_FOUND;
        StudentEntity student=repository.findById(id).get();

        if (studentReceiveDTO.getName()!=null)
            student.setName(studentReceiveDTO.getName());

        if (studentReceiveDTO.getGroupId()!=0L){
            Optional<GroupEntity> optional = groupRepository.findById(studentReceiveDTO.getGroupId());
            if (optional.isEmpty())
                return responseUtils.GROUP_NOT_FOUND;
            student.setGroup(optional.get());
        }

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(student));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        if (!check(id))
            return responseUtils.STUDENT_NOT_FOUND;
        repository.deleteById(id);
        return responseUtils.SUCCESS;
    }

    public ApiResponse subjectByStudentId(long id){
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.subjects(id));
        return success;
    }

    public ApiResponse studentById(String name){
        Optional<Object> byIdStudent = repository.findByIdStudent(name);
        if (byIdStudent.isEmpty())
            return responseUtils.STUDENT_NOT_FOUND;
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(byIdStudent.get());
        return success;
    }
}

package com.example.demo.service;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.JournalEntity;
import com.example.demo.model.receive.JournalReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.JournalRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService implements BaseService<JournalReceiveDTO> {
    private final JournalRepository repository;
    private final ModelMapper modelMapper;
    private final ResponseUtils responseUtils;
    private final GroupService groupService;
    private final GroupRepository groupRepository;

    public boolean check(String name){
        return repository.findByName(name).isPresent();
    }

    public boolean check(long id){
        return repository.findById(id).isPresent();
    }

    @Override
    public ApiResponse add(JournalReceiveDTO journalReceiveDTO) {
        if (check(journalReceiveDTO.getName()))
            return responseUtils.JOURNAL_EXISTS;
        if (!groupService.check(journalReceiveDTO.getGroupId()))
            return responseUtils.GROUP_NOT_FOUND;

        JournalEntity journal =modelMapper.map(journalReceiveDTO,JournalEntity.class);
        journal.setGroup(groupRepository.findById(journalReceiveDTO.getGroupId()).get());

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(journal));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        if (!check(id))
            return responseUtils.JOURNAL_NOT_FOUND;
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
    public ApiResponse update(long id, JournalReceiveDTO journalReceiveDTO) {
        if (!check(id))
            return responseUtils.JOURNAL_NOT_FOUND;
        JournalEntity journal=repository.findById(id).get();

        if (journalReceiveDTO.getName()!=null)
            journal.setName(journalReceiveDTO.getName());

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(journal));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        if (!check(id))
            return responseUtils.JOURNAL_NOT_FOUND;
        repository.deleteById(id);
        return responseUtils.SUCCESS;
    }
}

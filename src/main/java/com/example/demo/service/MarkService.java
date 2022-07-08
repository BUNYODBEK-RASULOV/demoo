package com.example.demo.service;

import com.example.demo.entity.MarkEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.model.receive.MarkReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.JournalPageRepository;
import com.example.demo.repository.MarkRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkService implements BaseService<MarkReceiveDTO> {
    private final MarkRepository repository;
    private final ModelMapper modelMapper;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final JournalPageService journalPageService;
    private final JournalPageRepository journalPageRepository;
    private final ResponseUtils responseUtils;

    @Override
    public ApiResponse add(MarkReceiveDTO markReceiveDTO) {
        MarkEntity mark=modelMapper.map(markReceiveDTO,MarkEntity.class);
        if (!studentService.check(markReceiveDTO.getStudentId()))
            return responseUtils.STUDENT_NOT_FOUND;
        if (!journalPageService.check(markReceiveDTO.getJournalPageId()))
            return responseUtils.JOURNAL_PAGE_NOT_FOUND;
        mark.setStudent(studentRepository.findById(markReceiveDTO.getStudentId()).get());
        mark.setJournalPage(journalPageRepository.findById(markReceiveDTO.getJournalPageId()).get());
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(mark));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        Optional<MarkEntity> optional = repository.findById(id);
        if (optional.isEmpty())
            return responseUtils.MARK_NOT_FOUND;
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(optional.get());
        return success;
    }

    @Override
    public ApiResponse list() {
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.findAll());
        return success;
    }

    @Override
    public ApiResponse update(long id, MarkReceiveDTO markReceiveDTO) {
        Optional<MarkEntity> optional = repository.findById(id);
        if (optional.isEmpty())
            return responseUtils.MARK_NOT_FOUND;
        MarkEntity mark=optional.get();
        if (markReceiveDTO.getScore()!=0D)
            mark.setScore(markReceiveDTO.getScore());
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(mark));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        Optional<MarkEntity> optional = repository.findById(id);
        if (optional.isEmpty())
            return responseUtils.MARK_NOT_FOUND;
        repository.delete(optional.get());
        return responseUtils.SUCCESS;
    }
}

package com.example.demo.service;

import com.example.demo.entity.JournalPageEntity;
import com.example.demo.model.receive.JournalPageReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.JournalPageRepository;
import com.example.demo.repository.JournalRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalPageService implements BaseService<JournalPageReceiveDTO> {
    private final JournalPageRepository repository;
    private final ResponseUtils responseUtils;
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;
    private final SubjectRepository subjectRepository;
    private final JournalService journalService;
    private final JournalRepository journalRepository;

    public boolean check(String name){
        return repository.findByName(name).isPresent();
    }
    public boolean check(long id){
        return repository.findById(id).isPresent();
    }

    @Override
    public ApiResponse add(JournalPageReceiveDTO journalPageReceiveDTO) {
        if (check(journalPageReceiveDTO.getName()))
            return responseUtils.JOURNAL_PAGE_EXISTS;
        if (!subjectService.check(journalPageReceiveDTO.getSubjectId()))
            return responseUtils.SUBJECT_NOT_FOUND;
        if (!journalService.check(journalPageReceiveDTO.getJournalId()))
            return responseUtils.JOURNAL_NOT_FOUND;

        JournalPageEntity journalPage=new JournalPageEntity();
        journalPage.setName(journalPageReceiveDTO.getName());
        journalPage.setSubject(subjectRepository.findById(journalPageReceiveDTO.getSubjectId()).get());
        journalPage.setJournal(journalRepository.findById(journalPageReceiveDTO.getJournalId()).get());
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(journalPage));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
        if (!check(id))
            return responseUtils.JOURNAL_PAGE_NOT_FOUND;
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
    public ApiResponse update(long id, JournalPageReceiveDTO journalPageReceiveDTO) {
        if (!check(id))
            return responseUtils.JOURNAL_PAGE_NOT_FOUND;
        JournalPageEntity journalPage=repository.findById(id).get();

        if (journalPageReceiveDTO.getName()!=null)
            journalPage.setName(journalPageReceiveDTO.getName());

        if (journalPageReceiveDTO.getSubjectId()!=0L){
            if (!subjectService.check(journalPageReceiveDTO.getSubjectId()))
                return responseUtils.SUBJECT_NOT_FOUND;
            journalPage.setSubject(subjectRepository.findById(journalPageReceiveDTO.getSubjectId()).get());
        }
        if (journalPageReceiveDTO.getJournalId()!=0L){
            if (!journalService.check(journalPageReceiveDTO.getJournalId()))
                return responseUtils.JOURNAL_NOT_FOUND;
            journalPage.setJournal(journalRepository.findById(journalPageReceiveDTO.getJournalId()).get());
        }
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(journalPage));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
        if (!check(id))
            return responseUtils.JOURNAL_PAGE_NOT_FOUND;
        repository.deleteById(id);
        return responseUtils.SUCCESS;
    }
}

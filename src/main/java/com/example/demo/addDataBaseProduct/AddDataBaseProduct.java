package com.example.demo.addDataBaseProduct;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddDataBaseProduct implements CommandLineRunner {
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final JournalRepository journalRepository;
    private final JournalPageRepository journalPageRepository;
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;

    @Override
    public void run(String... args) throws Exception {
        UniversityEntity university=universityRepository.save(new UniversityEntity("Uzbekiston milliy universetite","olmazor tumani",1991));
        FacultyEntity faculty=facultyRepository.save(new FacultyEntity("Fizika",university));
        GroupEntity group=groupRepository.save(new GroupEntity("F1805",2018,faculty));
        SubjectEntity subject=subjectRepository.save(new SubjectEntity("matematika"));
        SubjectEntity subject1=subjectRepository.save(new SubjectEntity("FIZIKA"));
        JournalEntity journal=journalRepository.save(new JournalEntity("F1805 JURNALI",group));
        JournalPageEntity journalPage=journalPageRepository.save(new JournalPageEntity("matematika uchun",subject,journal));
        JournalPageEntity journalPage1=journalPageRepository.save(new JournalPageEntity("fizika uchun",subject1,journal));
        StudentEntity student=studentRepository.save(new StudentEntity("jon",group));
        StudentEntity student1=studentRepository.save(new StudentEntity("BUNYODBEK",group));
        MarkEntity mark=markRepository.save(new MarkEntity(5,2019,student,journalPage));
        MarkEntity mark1=markRepository.save(new MarkEntity(4,2019,student,journalPage));
        MarkEntity mark2=markRepository.save(new MarkEntity(4,2019,student1,journalPage1));
    }
}

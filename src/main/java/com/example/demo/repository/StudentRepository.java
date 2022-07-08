package com.example.demo.repository;

import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.model.receive.StudentFacultyGroupDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    @Query("select s from student s where s.name = ?1")
    Optional<StudentEntity>findByName(String name);

    @Query(value = "select s2.name subject_name from student s\n" +
            "    left join groups g on s.group_id = g.id\n" +
            "    left join journal j on g.id = j.group_id\n" +
            "    left join journal_page jp on j.id = jp.journal_id\n" +
            "    left join  subject s2 on jp.subject_id = s2.id\n" +
            "where s.id = ?1",nativeQuery = true)
    List<SubjectEntity> subjects(long id);

    @Query(value = "select s.name student,f.name faculty,g.name groups  from student s\n" +
            "    left join groups g on s.group_id = g.id\n" +
            "    left join faculty f on g.faculty_id = f.id where s.name=?1",nativeQuery = true)
    Optional<Object>findByIdStudent(String name);
}

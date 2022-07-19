package com.example.demo.repository;

import com.example.demo.entity.FacultyEntity;
import com.example.demo.model.response.FacultyStudentCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyEntity,Long> {
    @Query("select f from faculty f where f.name = ?1")
    Optional<FacultyEntity>findByName(String name);


    @Query(value = "select g.name, count(s.id) as countStudent  from faculty f\n" +
            "    left join groups g on f.id = g.faculty_id\n" +
            "    left join student s on g.id = s.group_id\n" +
            "where f.id=?1 group by g.name; ", nativeQuery = true)
    List<FacultyStudentCount> groupAndStudentCountByFacultyId(long id);



}

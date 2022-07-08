package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Long> {
   Optional<GroupEntity>findByName(String name);
   @Query(value = "select s.name as student_name, AVG(m.score) avg from groups g\n" +
           "    left join student s on g.id = s.group_id\n" +
           "    left join  mark  m on s.id = m.student_id  " +
           "where g.id = ?1 group by student_name order by avg desc  ;",nativeQuery = true)
   List<Object> markAverageValueStudent(long id);
}

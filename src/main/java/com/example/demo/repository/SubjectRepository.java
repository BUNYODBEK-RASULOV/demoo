package com.example.demo.repository;

import com.example.demo.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
    Optional<SubjectEntity>findByName(String name);
}

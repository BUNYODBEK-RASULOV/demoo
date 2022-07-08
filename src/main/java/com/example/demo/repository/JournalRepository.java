package com.example.demo.repository;

import com.example.demo.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<JournalEntity,Long> {
    Optional<JournalEntity>findByName(String name);
}

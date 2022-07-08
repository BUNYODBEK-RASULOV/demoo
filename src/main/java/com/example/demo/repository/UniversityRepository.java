package com.example.demo.repository;

import com.example.demo.entity.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<UniversityEntity,Long> {
    @Query("select u from university u where u.name = ?1")
    Optional<UniversityEntity> findByName(String name);

    @Query("select u from university u where u.id = ?1")
    Optional<UniversityEntity>findById(long id);
}

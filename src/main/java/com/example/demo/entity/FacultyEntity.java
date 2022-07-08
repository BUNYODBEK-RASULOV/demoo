package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "faculty")
public class FacultyEntity extends BaseEntity {
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToOne
    private UniversityEntity university;

    @JsonProperty("groups")
    @OneToMany(mappedBy = "faculty")
    List<GroupEntity> groupEntities;

    public FacultyEntity(String name, UniversityEntity university) {
        this.name = name;
        this.university = university;
    }
}

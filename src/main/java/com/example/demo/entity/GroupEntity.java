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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class GroupEntity extends BaseEntity {
    @Column(unique = true)
    private String name;
    private int year;
    @JsonIgnore
    @ManyToOne
    private FacultyEntity faculty;

    @JsonProperty("journals")
    @OneToMany(mappedBy = "group")
    List<JournalEntity>journalEntities;

    public GroupEntity(String name, int year, FacultyEntity faculty) {
        this.name = name;
        this.year = year;
        this.faculty = faculty;
    }
}

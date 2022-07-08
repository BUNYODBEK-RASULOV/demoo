package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "university")
public class UniversityEntity extends BaseEntity{
    @Column(unique = true)
    private String name;
    private String address;

    @Column(name = "open_year")
    private int openYear;

    @JsonProperty("faculty")
    @OneToMany(mappedBy = "university")
    List<FacultyEntity> facultyEntities;


    public UniversityEntity(String name, String address, int openYear) {
        this.name = name;
        this.address = address;
        this.openYear = openYear;
    }
}

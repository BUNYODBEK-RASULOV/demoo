package com.example.demo.model.receive;

import com.example.demo.entity.UniversityEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyReceiveDTO {
    private String name;
    @JsonProperty("university_id")
    private long universityId;
}

package com.example.demo.model.receive;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupReceiveDTO {
    private String name;
    private int year;
    @JsonProperty("faculty_id")
    private long facultyId;
}

package com.example.demo.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityReceiveDTO {
    private String name;
    private String address;
    @JsonProperty("open_year")
    private int openYear;
}

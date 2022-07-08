package com.example.demo.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFacultyGroupDTO {
    private String student;
    private String faulty;
    private String groups;
}

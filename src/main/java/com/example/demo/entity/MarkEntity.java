package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mark")
public class MarkEntity extends BaseEntity {

    private double score;
    private int date;


    @ManyToOne
    private StudentEntity student;

    @JsonIgnore
    @ManyToOne
    private JournalPageEntity journalPage;

    public MarkEntity(double score, int date, StudentEntity student) {
        this.score = score;
        this.date = date;
        this.student = student;
    }
}

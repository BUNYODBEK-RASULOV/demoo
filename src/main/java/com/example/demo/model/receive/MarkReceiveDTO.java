package com.example.demo.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkReceiveDTO {
    private int date;
    private double score;
    @JsonProperty("journal_page_id")
    private long journalPageId;
    @JsonProperty("student_id")
    private long studentId;
}

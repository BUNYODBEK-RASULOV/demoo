package com.example.demo.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalPageReceiveDTO {
    private String name;
    @JsonProperty("subject_id")
    private long subjectId;
    @JsonProperty("journal_id")
    private long journalId;
}

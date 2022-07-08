package com.example.demo.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalReceiveDTO {
    private String name;
    @JsonProperty("group_id")
    private long groupId;
}

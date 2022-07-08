package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "journal")
public class JournalEntity extends BaseEntity {
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToOne
    private GroupEntity group;

    @JsonProperty("journal page")
    @OneToMany(mappedBy = "journal")
    List<JournalPageEntity>journalPageEntities;

    public JournalEntity(String name, GroupEntity group) {
        this.name = name;
        this.group = group;
    }
}

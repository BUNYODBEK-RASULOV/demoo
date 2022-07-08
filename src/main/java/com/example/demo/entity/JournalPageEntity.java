package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "journal_page")
public class JournalPageEntity extends BaseEntity{
    private String name;
    @ManyToOne
    private SubjectEntity subject;

    @JsonIgnore
    @ManyToOne
    private JournalEntity journal;

    @JsonProperty("marks")
    @OneToMany(mappedBy = "journalPage")
    List<MarkEntity>markEntities;

    public JournalPageEntity(String name, SubjectEntity subject, JournalEntity journal) {
        this.name = name;
        this.subject = subject;
        this.journal = journal;
    }
}

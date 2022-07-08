package com.example.demo.controller;

import com.example.demo.model.receive.JournalPageReceiveDTO;
import com.example.demo.service.JournalPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journalPage")
@RequiredArgsConstructor
public class JournalPageController implements BaseController<JournalPageReceiveDTO> {
    private final JournalPageService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody JournalPageReceiveDTO journalPageReceiveDTO) {
        return ResponseEntity.ok(service.add(journalPageReceiveDTO));
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<?> get(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/list")
    @Override
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.list());
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody JournalPageReceiveDTO journalPageReceiveDTO) {
        return ResponseEntity.ok(service.update(id,journalPageReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

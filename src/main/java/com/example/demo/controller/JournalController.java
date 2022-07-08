package com.example.demo.controller;

import com.example.demo.model.receive.JournalReceiveDTO;
import com.example.demo.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController implements BaseController<JournalReceiveDTO> {
    private final JournalService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody JournalReceiveDTO journalReceiveDTO) {
        return ResponseEntity.ok(service.add(journalReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody JournalReceiveDTO journalReceiveDTO) {
        return ResponseEntity.ok(service.update(id,journalReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }



}

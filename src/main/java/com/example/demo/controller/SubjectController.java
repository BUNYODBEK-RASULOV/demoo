package com.example.demo.controller;

import com.example.demo.model.receive.SubjectReceiveDTO;
import com.example.demo.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subject")
@RequiredArgsConstructor
public class SubjectController implements BaseController<SubjectReceiveDTO> {
    private final SubjectService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody SubjectReceiveDTO subjectReceiveDTO) {
        return ResponseEntity.ok(service.add(subjectReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody SubjectReceiveDTO subjectReceiveDTO) {
        return ResponseEntity.ok(service.update(id,subjectReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

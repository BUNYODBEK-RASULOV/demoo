package com.example.demo.controller;

import com.example.demo.model.receive.StudentReceiveDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController implements BaseController<StudentReceiveDTO> {
    private final StudentService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody StudentReceiveDTO studentReceiveDTO) {
        return ResponseEntity.ok(service.add(studentReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody StudentReceiveDTO studentReceiveDTO) {
        return ResponseEntity.ok(service.update(id,studentReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/subjectsByStudentId/{id}")
    public ResponseEntity<?> subjectByStudentId(@PathVariable long id){
        return ResponseEntity.ok(service.subjectByStudentId(id));
    }

    @GetMapping("/studentByName/{name}")
    public ResponseEntity<?> studentByName(@PathVariable String name){
        return ResponseEntity.ok(service.studentById(name));
    }
}


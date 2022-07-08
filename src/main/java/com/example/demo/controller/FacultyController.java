package com.example.demo.controller;

import com.example.demo.model.receive.FacultyReceiveDTO;
import com.example.demo.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController implements BaseController<FacultyReceiveDTO> {
    private final FacultyService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody FacultyReceiveDTO facultyReceiveDTO) {
        return ResponseEntity.ok(service.add(facultyReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody FacultyReceiveDTO facultyReceiveDTO) {
        return ResponseEntity.ok(service.update(id,facultyReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/groupAndStudentCountByFacultyId/{id}")
    public ResponseEntity<?> groupAndStudentCountByFacultyId(@PathVariable long id){
        return ResponseEntity.ok(service.groupAndStudentCountByFacultyId(id));
    }
}

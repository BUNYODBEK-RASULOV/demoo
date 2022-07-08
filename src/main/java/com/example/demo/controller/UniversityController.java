package com.example.demo.controller;

import com.example.demo.model.receive.UniversityReceiveDTO;
import com.example.demo.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/university")
@RequiredArgsConstructor
public class UniversityController implements BaseController<UniversityReceiveDTO> {
    private final UniversityService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody UniversityReceiveDTO universityReceiveDTO) {
        return ResponseEntity.ok(service.add(universityReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody UniversityReceiveDTO universityReceiveDTO) {
        return ResponseEntity.ok(service.update(id,universityReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

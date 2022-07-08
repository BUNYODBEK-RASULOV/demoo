package com.example.demo.controller;

import com.example.demo.model.receive.MarkReceiveDTO;
import com.example.demo.model.receive.StudentReceiveDTO;
import com.example.demo.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mark")
@RequiredArgsConstructor
public class MarkController implements BaseController<MarkReceiveDTO> {
    private final MarkService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody MarkReceiveDTO markReceiveDTO) {
        return ResponseEntity.ok(service.add(markReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody MarkReceiveDTO markReceiveDTO) {
        return ResponseEntity.ok(service.update(id, markReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

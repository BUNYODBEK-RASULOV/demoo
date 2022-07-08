package com.example.demo.controller;

import com.example.demo.model.receive.GroupReceiveDTO;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController implements BaseController<GroupReceiveDTO> {
    private final GroupService service;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody GroupReceiveDTO groupReceiveDTO) {
        return ResponseEntity.ok(service.add(groupReceiveDTO));
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
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody GroupReceiveDTO groupReceiveDTO) {
        return ResponseEntity.ok(service.update(id,groupReceiveDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/markAverageValueStudent/{id}")
    public ResponseEntity<?> markAverageValueStudent(@PathVariable long id){
        return ResponseEntity.ok(service.markAverageValueStudent(id));
    }
}

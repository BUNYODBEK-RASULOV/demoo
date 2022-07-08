package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BaseController<T> {

    ResponseEntity<?> add(T t);
    ResponseEntity<?>get(long id);
    ResponseEntity<?>list();
    ResponseEntity<?>update(long id,T t);
    ResponseEntity<?>delete(long id);

}

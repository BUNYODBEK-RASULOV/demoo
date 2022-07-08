package com.example.demo.service;

import com.example.demo.model.response.ApiResponse;
import org.springframework.stereotype.Component;

@Component
public interface BaseService<T> {

    ApiResponse add(T t);
    ApiResponse get(long id);
    ApiResponse list();
    ApiResponse update(long id, T t);
    ApiResponse delete(long id);
}

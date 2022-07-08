package com.example.demo.util;

import com.example.demo.model.response.ApiResponse;
import org.springframework.stereotype.Component;


@Component
public class ResponseUtils {

    public ApiResponse SUCCESS = new ApiResponse(0, "success");
    public ApiResponse UNIVERSITY_NOT_FOUND = new ApiResponse(-1, "university not found");
    public ApiResponse UNIVERSITY_EXISTS = new ApiResponse(-2, "university exists");
    public ApiResponse FACULTY_NOT_FOUND = new ApiResponse(-1, "faculty not found");
    public ApiResponse FACULTY_EXISTS = new ApiResponse(-2, "faculty exists");
    public ApiResponse GROUP_NOT_FOUND = new ApiResponse(-1, "group not found");
    public ApiResponse GROUP_EXISTS = new ApiResponse(-2, "group exists");
    public ApiResponse STUDENT_NOT_FOUND = new ApiResponse(-1, "student not found");
    public ApiResponse STUDENT_EXISTS = new ApiResponse(-2, "student exists");
    public ApiResponse JOURNAL_NOT_FOUND = new ApiResponse(-1, "journal not found");
    public ApiResponse JOURNAL_EXISTS = new ApiResponse(-2, "journal exists");
    public ApiResponse SUBJECT_NOT_FOUND = new ApiResponse(-1, "subject not found");
    public ApiResponse SUBJECT_EXISTS = new ApiResponse(-2, "subject exists");
    public ApiResponse JOURNAL_PAGE_NOT_FOUND = new ApiResponse(-1, "journal page not found");
    public ApiResponse JOURNAL_PAGE_EXISTS = new ApiResponse(-2, "journal page exists");
    public ApiResponse MARK_NOT_FOUND = new ApiResponse(-1, "mark  not found");
    public ApiResponse MARK_EXISTS = new ApiResponse(-2, "mark exists");
}
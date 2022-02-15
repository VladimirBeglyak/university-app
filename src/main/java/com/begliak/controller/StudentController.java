package com.begliak.controller;

import com.begliak.dto.StudentAndDayDto;
import com.begliak.dto.StudentWithLectionsDto;
import com.begliak.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/uni/")
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public StudentWithLectionsDto getStudent(@RequestBody StudentAndDayDto studentAndDayDto) {
        return studentService.getStudentAndLections(studentAndDayDto);
    }

}

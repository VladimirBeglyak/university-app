package com.begliak.service;

import com.begliak.dto.StudentAndDayDto;
import com.begliak.dto.StudentDto;
import com.begliak.dto.StudentWithLectionsDto;
import com.begliak.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<StudentDto,Long> {

    StudentWithLectionsDto getStudentAndLections(StudentAndDayDto studentAndDayDto);

}

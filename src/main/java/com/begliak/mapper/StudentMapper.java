package com.begliak.mapper;

import com.begliak.dto.StudentDto;
import com.begliak.entity.Groop;
import com.begliak.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<Student, StudentDto> {

    @Override
    public StudentDto toDto(Student student) {
        return new StudentDto(student.getFirstName(), student.getLastName(), student.getGroop().getNumber());
    }

    @Override
    public Student fromDto(StudentDto studentDto) {
        return new Student(studentDto.firstName(), studentDto.lastName(),
                Groop.builder().number(studentDto.groop()).build());
    }

}

package com.begliak.service;

import com.begliak.dto.StudentAndDayDto;
import com.begliak.dto.StudentDto;
import com.begliak.dto.StudentWithLectionsDto;
import com.begliak.entity.Day;
import com.begliak.entity.Lection;
import com.begliak.entity.Student;
import com.begliak.exception.NotFoundException;
import com.begliak.mapper.LectionMapper;
import com.begliak.mapper.StudentMapper;
import com.begliak.repository.LectionRepository;
import com.begliak.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final LectionRepository lectionRepository;
    private final LectionMapper lectionMapper;
    private final StudentMapper studentMapper;


    @Override
    public StudentDto getById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Student with id %s not found", id)));
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(toList());
    }

    @Override
    public StudentDto update(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student with id %s not found", id)));

        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        studentRepository.save(student);

        return studentDto;
    }

    @Override
    public StudentDto delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student with id %s not found", id)));

        studentRepository.delete(student);

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto add(StudentDto studentDto) {
        Student student = studentMapper.fromDto(studentDto);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toDto(savedStudent);
    }

    @Override
    public StudentWithLectionsDto getStudentAndLections(StudentAndDayDto studentAndDayDto) {

        Student student = studentRepository.findByFirstNameAndLastName(studentAndDayDto.firstName(), studentAndDayDto.lastName())
                .orElseThrow(() -> new NotFoundException("Student no found"));

        List<Lection> allLectionsByStudentAndDay = lectionRepository.findAllByStudentAndDay(student.getId(), Day.valueOf(studentAndDayDto.day()));

        return getStudent(student, allLectionsByStudentAndDay);
    }

    private StudentWithLectionsDto getStudent(Student student, List<Lection> allLectionsByStudentAndDay) {
        return new StudentWithLectionsDto(student.getFirstName(),
                student.getLastName(),
                student.getGroop().getNumber(),
                allLectionsByStudentAndDay.stream().map(lectionMapper::toDto).collect(toList()));
    }
}

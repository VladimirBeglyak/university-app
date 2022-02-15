package com.begliak.service;

import com.begliak.dto.LectionDto;
import com.begliak.dto.StudentAndDayDto;
import com.begliak.dto.StudentDto;
import com.begliak.dto.StudentWithLectionsDto;
import com.begliak.entity.Day;
import com.begliak.entity.Groop;
import com.begliak.entity.Lection;
import com.begliak.entity.Student;
import com.begliak.mapper.LectionMapper;
import com.begliak.mapper.StudentMapper;
import com.begliak.repository.LectionRepository;
import com.begliak.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private LectionRepository lectionRepository;
    @Mock
    private LectionMapper lectionMapper;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private StudentServiceImpl studentService;


    @Test
    void testTetStudentById() {

        Student student = new Student("Vladimir", "Begliak", Groop.builder().number("Test").build());
        student.setId(1L);
        doReturn(Optional.of(student)).when(studentRepository).findById(1L);

        StudentDto expectedResult = new StudentDto("Vladimir", "Begliak", "Test");
        doReturn(expectedResult).when(studentMapper).toDto(student);

        StudentDto actualResult = studentService.getById(1L);

        assertEquals(expectedResult, actualResult);

        verifyNoMoreInteractions(lectionRepository, lectionMapper);
    }

    @Test
    void testGetStudents() {

        Student student1 = new Student("Vladimir", "Begliak", Groop.builder().number("Test").build());
        Student student2 = new Student("Max", "Popov", Groop.builder().number("Test").build());
        List<Student> students = asList(student1, student2);
        doReturn(students).when(studentRepository).findAll();

        StudentDto studentDto1 = new StudentDto("Vladimir", "Begliak", "Test");
        StudentDto studentDto2 = new StudentDto("Max", "Popov", "Test");
        List<StudentDto> expectedResult = asList(studentDto1, studentDto2);
        doReturn(studentDto1).when(studentMapper).toDto(student1);
        doReturn(studentDto2).when(studentMapper).toDto(student2);

        List<StudentDto> actualResult = studentService.getAll();

        assertEquals(expectedResult, actualResult);
        verifyNoMoreInteractions(lectionRepository, lectionMapper);
    }

    @Test
    void testUpdateStudent() {

        Student student = new Student("Vladimir", "Popov", Groop.builder().number("Test").build());
        student.setId(1L);

        doReturn(Optional.of(student)).when(studentRepository).findById(1L);

        StudentDto studentDto = new StudentDto("Vladimir", "Begliak", "Test");

        StudentDto actualResult = studentService.update(1L, studentDto);

        assertEquals(studentDto, actualResult);
        verifyNoMoreInteractions(lectionRepository, lectionMapper);
    }

    @Test
    void testDeleteStudent() {

        Student student = new Student("Vladimir", "Begliak", Groop.builder().number("Test").build());
        student.setId(1L);
        StudentDto expectedResult = new StudentDto("Vladimir", "Begliak", "Test");

        doReturn(Optional.of(student)).when(studentRepository).findById(1L);
        doReturn(expectedResult).when(studentMapper).toDto(student);

        StudentDto actualResult = studentService.delete(1L);

        assertEquals(expectedResult, actualResult);
        verifyNoMoreInteractions(lectionRepository, lectionMapper);
    }

    @Test
    void TestAddStudent() {
        Student student = new Student("Vladimir", "Begliak", Groop.builder().number("TestNumber").build());
        student.setId(1L);

        doReturn(student).when(studentRepository).save(student);

        StudentDto expectedResult = new StudentDto("Vladimir", "Begliak", "TestNumber");
        StudentDto studentDto = new StudentDto("Vladimir", "Begliak", "TestNumber");
        doReturn(student).when(studentMapper).fromDto(studentDto);
        doReturn(expectedResult).when(studentMapper).toDto(student);

        StudentDto actualResult = studentService.add(studentDto);

        assertEquals(expectedResult, actualResult);
        verifyNoMoreInteractions(lectionRepository, lectionMapper);
    }

    @Test
    void testGetStudentWithLections() {

        StudentAndDayDto studentAndDayDto = new StudentAndDayDto("Vladimir", "Beglyak", "MONDAY");

        Student student = new Student("Vladimir", "Begliak", Groop.builder().number("Test").build());
        student.setId(1L);
        doReturn(Optional.of(student)).when(studentRepository).findByFirstNameAndLastName(studentAndDayDto.firstName(), studentAndDayDto.lastName());

        List<Lection> lections = asList(new Lection("Physik", Day.valueOf(studentAndDayDto.day()), Groop.builder().number("Test").build()),
                new Lection("Music", Day.valueOf(studentAndDayDto.day()), Groop.builder().number("Test").build()));

        LectionDto lectionsDto1 = new LectionDto("Physik", "Monday", "Test");
        LectionDto lectionsDto2 = new LectionDto("Music", "Monday", "Test");

        StudentWithLectionsDto expectedResult = new StudentWithLectionsDto("Vladimir", "Begliak", "Test", asList(lectionsDto1, lectionsDto2));

        doReturn(lections).when(lectionRepository).findAllByStudentAndDay(student.getId(), Day.valueOf(studentAndDayDto.day()));
        doReturn(lectionsDto1).when(lectionMapper).toDto(lections.get(0));
        doReturn(lectionsDto2).when(lectionMapper).toDto(lections.get(1));

        StudentWithLectionsDto actualResult = studentService.getStudentAndLections(studentAndDayDto);

        assertEquals(expectedResult, actualResult);
    }
}
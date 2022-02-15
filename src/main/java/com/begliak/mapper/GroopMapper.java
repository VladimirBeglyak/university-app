package com.begliak.mapper;

import com.begliak.dto.GroopDto;
import com.begliak.dto.LectionDto;
import com.begliak.dto.StudentDto;
import com.begliak.entity.Day;
import com.begliak.entity.Groop;
import com.begliak.entity.Lection;
import com.begliak.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class GroopMapper implements Mapper<Groop, GroopDto> {

    @Override
    public GroopDto toDto(Groop groop) {
        return new GroopDto(groop.getNumber(),
                groop.getStudents().stream()
                        .map(student -> new StudentDto(student.getFirstName(), student.getLastName(), groop.getNumber())).
                        collect(toList()),
                groop.getLections().stream()
                        .map(lection -> new LectionDto(lection.getName(), StringUtils.capitalize(lection.getDay().name().toLowerCase()), lection.getGroop().getNumber()))
                        .collect(toList()));
    }

    @Override
    public Groop fromDto(GroopDto groopDto) {
        return Groop.builder()
                .number(groopDto.number())
                .lections(groopDto.lections().stream().map(dto -> new Lection(dto.name(), Day.valueOf(dto.day()), Groop.builder().number(dto.groop()).build()))
                        .collect(toList()))
                .students(groopDto.students().stream()
                        .map(studentDto -> new Student(studentDto.firstName(), studentDto.lastName(), Groop.builder().number(studentDto.groop()).build()))
                        .collect(toList()))
                .build();
    }
}

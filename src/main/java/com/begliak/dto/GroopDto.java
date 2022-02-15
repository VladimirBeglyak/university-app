package com.begliak.dto;

import java.util.List;

public record GroopDto(
        String number,
        List<StudentDto> students,
        List<LectionDto> lections) {
}

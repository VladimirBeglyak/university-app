package com.begliak.dto;

import java.util.List;

public record StudentWithLectionsDto(String firstName,
                                     String lastName,
                                     String groop,
                                     List<LectionDto> lections) {

}

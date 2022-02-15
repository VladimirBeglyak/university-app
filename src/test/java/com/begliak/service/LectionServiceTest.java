package com.begliak.service;

import com.begliak.dto.GroopDto;
import com.begliak.dto.LectionDto;
import com.begliak.entity.Day;
import com.begliak.entity.Groop;
import com.begliak.entity.Lection;
import com.begliak.mapper.LectionMapper;
import com.begliak.repository.LectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectionServiceTest {

    @Mock
    private LectionRepository lectionRepository;

    @Mock
    private LectionMapper lectionMapper;

    @InjectMocks
    private LectionService lectionService;

    @Test
    void testGetLection() {
        Lection lection = new Lection("Test", Day.MONDAY, Groop.builder().number("Test").build());
        lection.setId(1L);
        doReturn(Optional.of(lection)).when(lectionRepository).findById(1L);

        LectionDto expectedResult = new LectionDto("Test","Monday","Test");
        doReturn(expectedResult).when(lectionMapper).toDto(lection);

        LectionDto actualResult = lectionService.getById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLections() {

        Lection lection1 = new Lection("Test", Day.MONDAY, Groop.builder().number("Test").build());
        Lection lection2 = new Lection("Test1", Day.MONDAY, Groop.builder().number("Test").build());
        List<Lection> lections = Arrays.asList(lection1, lection2);
        doReturn(lections).when(lectionRepository).findAll();

        LectionDto lectionDto1 = new LectionDto("Test", "Monday", "Test");
        LectionDto lectionDto2 = new LectionDto("Test1", "Monday", "Test");
        List<LectionDto> expectedResult = Arrays.asList(lectionDto1,lectionDto2);
        doReturn(lectionDto1).when(lectionMapper).toDto(lection1);
        doReturn(lectionDto2).when(lectionMapper).toDto(lection2);

        List<LectionDto> actualResult = lectionService.getAll();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testUpdateLection() {

        Lection lection = new Lection("OldName", Day.MONDAY, Groop.builder().number("Test").build());
        lection.setId(1L);
        doReturn(Optional.of(lection)).when(lectionRepository).findById(1L);

        LectionDto lectionDto = new LectionDto("NewName", "Monday", "Test");

        LectionDto actualResult = lectionService.update(1L, lectionDto);

        assertEquals(lectionDto, actualResult);
    }

    @Test
    void testDeleteLection() {

        Lection lection = new Lection("Test", Day.MONDAY, Groop.builder().number("Test").build());
        lection.setId(1L);
        LectionDto expectedResult = new LectionDto("Test", "Monday", "Test");

        doReturn(Optional.of(lection)).when(lectionRepository).findById(1L);
        doReturn(expectedResult).when(lectionMapper).toDto(lection);

        LectionDto actualResult = lectionService.delete(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testAddLection() {

        Lection lection = new Lection("Test", Day.MONDAY, Groop.builder().number("TestNumber").build());
        lection.setId(1L);

        doReturn(lection).when(lectionRepository).save(lection);

        LectionDto expectedResult = new LectionDto("Test", "Monday", "TestNumber");
        LectionDto lectionDto = new LectionDto("Test", "Monday", "TestNumber");
        doReturn(lection).when(lectionMapper).fromDto(lectionDto);
        doReturn(expectedResult).when(lectionMapper).toDto(lection);

        LectionDto actualResult = lectionService.add(lectionDto);

        assertEquals(expectedResult, actualResult);

    }
}
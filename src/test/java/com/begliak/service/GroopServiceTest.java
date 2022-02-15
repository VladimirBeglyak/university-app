package com.begliak.service;

import com.begliak.dto.GroopDto;
import com.begliak.entity.Groop;
import com.begliak.mapper.GroopMapper;
import com.begliak.repository.GroopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GroopServiceTest {

    @Mock
    private GroopRepository groopRepository;

    @Mock
    private GroopMapper groopMapper;

    @InjectMocks
    private GroopService groopService;

    @Test
    void testGetGroop() {
        Groop groop = new Groop("Test", Collections.emptyList(), Collections.emptyList());
        groop.setId(1L);
        doReturn(Optional.of(groop)).when(groopRepository).findById(1L);

        GroopDto expectedResult = new GroopDto("Test", Collections.emptyList(), Collections.emptyList());
        doReturn(expectedResult).when(groopMapper).toDto(groop);

        GroopDto actualResult = groopService.getById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetGroops() {

        Groop groop1 = new Groop("Test", Collections.emptyList(), Collections.emptyList());
        Groop groop2 = new Groop("Test1", Collections.emptyList(), Collections.emptyList());
        List<Groop> groops = asList(groop1, groop2);
        doReturn(groops).when(groopRepository).findAll();

        GroopDto groopDto1 = new GroopDto("Test", Collections.emptyList(), Collections.emptyList());
        GroopDto groopDto2 = new GroopDto("Test1", Collections.emptyList(), Collections.emptyList());
        List<GroopDto> expectedResult = asList(groopDto1, groopDto2);
        doReturn(groopDto1).when(groopMapper).toDto(groop1);
        doReturn(groopDto2).when(groopMapper).toDto(groop2);

        List<GroopDto> actualResult = groopService.getAll();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testUpdateGroop() {

        Groop groop = new Groop("OldName", Collections.emptyList(), Collections.emptyList());
        groop.setId(1L);
        doReturn(Optional.of(groop)).when(groopRepository).findById(1L);

        GroopDto groopDto = new GroopDto("NewName", Collections.emptyList(), Collections.emptyList());

        GroopDto actualResult = groopService.update(1L, groopDto);

        assertEquals(groopDto, actualResult);
    }

    @Test
    void testDeleteGroop() {

        Groop groop = new Groop("Test", Collections.emptyList(), Collections.emptyList());
        groop.setId(1L);

        GroopDto expectedResult = new GroopDto("Test", Collections.emptyList(), Collections.emptyList());

        doReturn(Optional.of(groop)).when(groopRepository).findById(1L);
        doReturn(expectedResult).when(groopMapper).toDto(groop);

        GroopDto actualResult = groopService.delete(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testAddGroop() {

        Groop groop = new Groop("Test", Collections.emptyList(), Collections.emptyList());
        groop.setId(1L);

        doReturn(groop).when(groopRepository).save(groop);

        GroopDto expectedResult = new GroopDto("Test", Collections.emptyList(), Collections.emptyList());
        GroopDto groopDto = new GroopDto("Test", Collections.emptyList(), Collections.emptyList());
        doReturn(groop).when(groopMapper).fromDto(groopDto);
        doReturn(expectedResult).when(groopMapper).toDto(groop);

        GroopDto actualResult = groopService.add(groopDto);

        assertEquals(expectedResult, actualResult);
    }
}
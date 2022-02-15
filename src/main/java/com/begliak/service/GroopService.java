package com.begliak.service;

import com.begliak.dto.GroopDto;
import com.begliak.entity.Groop;
import com.begliak.exception.NotFoundException;
import com.begliak.mapper.GroopMapper;
import com.begliak.repository.GroopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class GroopService implements BaseService<GroopDto, Long> {

    private final GroopRepository groopRepository;

    private final GroopMapper groopMapper;

    @Override
    public GroopDto getById(Long id) {
        return groopRepository.findById(id)
                .map(groopMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Groop with id %s not found", id)));
    }

    @Override
    public List<GroopDto> getAll() {
        return groopRepository.findAll().stream()
                .map(groopMapper::toDto)
                .collect(toList());
    }

    @Override
    public GroopDto update(Long id, GroopDto groopDto) {
        Groop groop = groopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Groop with id %s not found", id)));

        groop.setNumber(groopDto.number());
        groopRepository.save(groop);

        return groopDto;
    }

    @Override
    public GroopDto delete(Long id) {
        Groop groop = groopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Groop with id %s not found", id)));

        groopRepository.delete(groop);

        return groopMapper.toDto(groop);
    }

    @Override
    public GroopDto add(GroopDto groopDto) {
        Groop groop = groopMapper.fromDto(groopDto);
        Groop savedGroop = groopRepository.save(groop);

        return groopMapper.toDto(savedGroop);
    }
}

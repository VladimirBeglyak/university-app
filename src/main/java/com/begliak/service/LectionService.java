package com.begliak.service;

import com.begliak.dto.LectionDto;
import com.begliak.entity.Groop;
import com.begliak.entity.Lection;
import com.begliak.exception.NotFoundException;
import com.begliak.mapper.LectionMapper;
import com.begliak.repository.LectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class LectionService implements BaseService<LectionDto, Long> {

    private final LectionRepository lectionRepository;
    private final LectionMapper lectionMapper;

    @Override
    public LectionDto getById(Long id) {
        return lectionRepository.findById(id)
                .map(lectionMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Lection with id %s not found", id)));
    }

    @Override
    public List<LectionDto> getAll() {
        return lectionRepository.findAll().stream()
                .map(lectionMapper::toDto)
                .collect(toList());
    }

    @Override
    public LectionDto update(Long id, LectionDto lectionDto) {
        Lection lection = lectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Lection with id %s not found", id)));

        lection.setGroop(Groop.builder()
                .number(lectionDto.groop())
                .build());

        lectionRepository.save(lection);

        return lectionDto;
    }

    @Override
    public LectionDto delete(Long id) {
        Lection lection = lectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Lection with id %s not found", id)));

        lectionRepository.delete(lection);

        return lectionMapper.toDto(lection);
    }

    @Override
    public LectionDto add(LectionDto lectionDto) {

        Lection lection = lectionMapper.fromDto(lectionDto);
        Lection savedGroop = lectionRepository.save(lection);

        return lectionMapper.toDto(savedGroop);
    }
}

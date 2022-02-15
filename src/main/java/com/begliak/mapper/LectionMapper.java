package com.begliak.mapper;

import com.begliak.dto.LectionDto;
import com.begliak.entity.Day;
import com.begliak.entity.Groop;
import com.begliak.entity.Lection;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LectionMapper implements Mapper<Lection, LectionDto> {

    @Override
    public LectionDto toDto(Lection lection) {
        return new LectionDto(lection.getName(),
                StringUtils.capitalize(lection.getDay().name().toLowerCase()),
                lection.getGroop().getNumber());
    }

    @Override
    public Lection fromDto(LectionDto lectionDto) {
        return new Lection(lectionDto.name(), Day.valueOf(lectionDto.day()), Groop.builder().number(lectionDto.groop()).build());
    }

}

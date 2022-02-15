package com.begliak.service;

import com.begliak.dto.GroopDto;

import java.util.List;

public interface BaseService<R,I>{

    R getById(I id);

    List<R> getAll();

    R update(I id, R r);

    R delete(I id);

    R add(R r);
}

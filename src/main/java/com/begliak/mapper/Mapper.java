package com.begliak.mapper;

public interface Mapper <E,R>{
    R toDto(E e);
    E fromDto(R r);
}

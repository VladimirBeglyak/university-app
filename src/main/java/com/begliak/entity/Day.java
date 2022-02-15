package com.begliak.entity;

import java.util.Optional;

public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    public static String findDay(String day) {
        return Optional.of(day)
                .filter(s -> s.equals(Day.valueOf(day).name()))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Day %s not found", day)));
    }
}

package com.begliak.exception;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ErrorResponse {
    Integer status;
    String message;
}

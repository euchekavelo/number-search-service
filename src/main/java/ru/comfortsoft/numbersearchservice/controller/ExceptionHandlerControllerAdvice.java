package ru.comfortsoft.numbersearchservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.comfortsoft.numbersearchservice.dto.ResponseDto;
import ru.comfortsoft.numbersearchservice.exception.IncorrectFileFormatException;
import ru.comfortsoft.numbersearchservice.exception.NumberNotFoundException;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(NumberNotFoundException.class)
    public ResponseEntity<ResponseDto> handleExceptionForNotFoundHttpStatus(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getResponseDto(ex.getMessage()));
    }

    @ExceptionHandler({IOException.class, IncorrectFileFormatException.class})
    public ResponseEntity<ResponseDto> handleExceptionForBadRequestHttpStatus(Exception ex) {
        return ResponseEntity.badRequest().body(getResponseDto(ex.getMessage()));
    }

    private ResponseDto getResponseDto(String message) {
        return ResponseDto.builder().result(false).message(message).build();
    }
}

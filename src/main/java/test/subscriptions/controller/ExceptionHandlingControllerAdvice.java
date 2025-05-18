package test.subscriptions.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import test.subscriptions.dto.ApiErrorResponseDto;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {

    private ResponseEntity<ApiErrorResponseDto> buildResponseEntity(
            ApiErrorResponseDto apiErrorResponseDto) {
        return ResponseEntity.status(apiErrorResponseDto.getStatus().value())
                .body(apiErrorResponseDto);
    }

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponseDto> onNoSuchElementException(
            NoSuchElementException e
    ) {
        return buildResponseEntity(new ApiErrorResponseDto(HttpStatus.NOT_FOUND, e.getMessage(), e));
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponseDto> onIllegalArgumentException(
            RuntimeException e) {
        return buildResponseEntity(
                new ApiErrorResponseDto(HttpStatus.BAD_REQUEST, e.getMessage(), e));
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponseDto> handleBadInput(HttpMessageNotReadableException e) {
        return buildResponseEntity(
                new ApiErrorResponseDto(HttpStatus.BAD_REQUEST, e.getMessage(), e));
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponseDto> handleBadInput(DataIntegrityViolationException e) {
        return buildResponseEntity(
                new ApiErrorResponseDto(HttpStatus.BAD_REQUEST, e.getMessage(), e));
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponseDto> handleException(Exception e) {
        return buildResponseEntity(
                new ApiErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getMessage(), e));
    }
}

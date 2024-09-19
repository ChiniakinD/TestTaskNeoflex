package com.chiniakin.exception;

import com.chiniakin.model.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений.
 *
 * @author ChiniakinD
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывет исключение {@link IncorrectDateFormatException} .
     *
     * @param e исключение {@link IncorrectDateFormatException} .
     * @return ответ с сообщением об ошибке и код статуса 400.
     */
    @ExceptionHandler(IncorrectDateFormatException.class)
    public ResponseEntity<ApiError> handleIncorrectDateFormatException(IncorrectDateFormatException e) {
        return buildExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "Некорректный формат данных",
                e
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException e) {
        return buildExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "Некорректный формат данных",
                e
        );
    }

    /**
     * Создает объект с информацией об ошибке.
     *
     * @param httpStatus статус ответа.
     * @param errorText  текст сообщения об ошибке.
     * @param throwable  исключение, содержащее информацию об ошибке.
     * @return объеки {@link ApiError}.
     */
    private ResponseEntity<ApiError> buildExceptionResponse(HttpStatus httpStatus, String errorText, Throwable throwable) {
        ApiError apiError = new ApiError()
                .setStatus(httpStatus.getReasonPhrase())
                .setDetails(throwable.getMessage())
                .setMessage(errorText);
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiError);
    }

}

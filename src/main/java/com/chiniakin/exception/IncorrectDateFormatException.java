package com.chiniakin.exception;

/**
 * Исключение с сообщением, о некорректном формате даты.
 *
 * @author ChiniakinD
 */
public class IncorrectDateFormatException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public IncorrectDateFormatException(String message) {
        super(message);
    }

}

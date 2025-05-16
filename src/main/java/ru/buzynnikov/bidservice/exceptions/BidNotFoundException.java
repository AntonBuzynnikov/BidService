package ru.buzynnikov.bidservice.exceptions;

/**
 * Исключение, возникающее при отсутствии заявки.
 * Наследуется от RuntimeException, так как обработка ошибок должна происходить немедленно.
 */
public class BidNotFoundException extends RuntimeException{
    public BidNotFoundException(String message) {
        super(message);
    }
}

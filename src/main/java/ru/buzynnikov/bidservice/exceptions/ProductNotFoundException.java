package ru.buzynnikov.bidservice.exceptions;

/**
 * Исключение, которое возникает, когда запрашиваемый товар не найден.
 * Это исключение наследуется от RuntimeException, что позволяет обрабатывать его сразу же в процессе выполнения программы.
 */
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}

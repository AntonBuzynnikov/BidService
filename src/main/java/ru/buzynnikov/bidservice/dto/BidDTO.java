package ru.buzynnikov.bidservice.dto;

import lombok.Data;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.security.models.User;

import java.util.List;

/**
 * DTO-класс для передачи данных о заявке (bid).
 * Включает список продуктов и пользователя, создавшего ставку.
 */
@Data
public class BidDTO {
    private List<Product> products;
    private User user;
}

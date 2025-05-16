package ru.buzynnikov.bidservice.dto;

import lombok.Data;

/**
 * DTO-класс для представления данных о продукте.
 * Используется для добавления или обновления информации о продуктах.
 */
@Data
public class ProductDTO {
    private String name;
    private double weight;
    private double price;
}

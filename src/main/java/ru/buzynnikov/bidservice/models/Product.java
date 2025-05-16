package ru.buzynnikov.bidservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Модель товара (продукта).
 * Содержит основную информацию о продукте, такую как название, вес и цена.
 */
@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal weight;
    @Column(nullable = false)
    private BigDecimal price;
}

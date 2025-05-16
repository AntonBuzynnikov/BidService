package ru.buzynnikov.bidservice.models;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Класс {@code Bid}, представляющий сущность заявки (bid).
 * Используется для хранения информации о заявках в приложении.
 */
@Data
@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "time_create")
    private LocalDateTime timeCreate;
    private BigDecimal sum;
}

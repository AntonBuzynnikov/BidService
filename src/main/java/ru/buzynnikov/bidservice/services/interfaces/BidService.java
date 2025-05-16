package ru.buzynnikov.bidservice.services.interfaces;

import ru.buzynnikov.bidservice.models.Bid;
import ru.buzynnikov.bidservice.models.Product;

import java.util.HashMap;

/**
 * Интерфейс службы заявок (BidService).
 * Определяет контракт для работы с заявками.
 */
public interface BidService {

    /**
     * Отправляет заявку.
     * Возвращает сформированную заявку в виде мапы продуктов и их требуемого количества.
     *
     * @return Карта продуктов и соответствующих им количеств
     */
    HashMap<Product, Double> sendBid();

    /**
     * Получает заявку по её идентификатору.
     *
     * @param id Идентификатор заявки
     * @return Заявка с соответствующим идентификатором
     */
    Bid getBidById(Long id);

}

package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Consumption;

/**
 * Интерфейс репозитория для работы с моделью потребления (Consumptions).
 * Предоставляет стандартный набор методов CRUD для манипуляции данными потребления.
 */
@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Long> {
}

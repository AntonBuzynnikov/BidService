package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Quantity;

/**
 * Интерфейс репозитория для работы с сущностью "Количество" (Quantity).
 * Реализует стандартную функциональность JpaRepository для CRUD-операций с объектами Quantity.
 */
@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
}

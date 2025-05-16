package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Bid;

/**
 * Репозиторий для работы с сущностью "Заявка" (Bid).
 * Реализует интерфейс JpaRepository, предоставляя стандартные CRUD-методы для работы с объектами Bid.
 */
@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
}

package ru.buzynnikov.bidservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.models.Product;

import java.util.Optional;

/**
 * Интерфейс репозитория для работы с моделями продуктов (Products).
 * Расширяет CrudRepository, обеспечивая базовые операции CRUD над объектами Product.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    /**
     * Поиск продукта по имени.
     *
     * @param name Название продукта
     * @return Optional, содержащий продукт с указанным именем или пустой Optional, если продукт не найден
     */
    Optional<Product> findByName(String name);
}

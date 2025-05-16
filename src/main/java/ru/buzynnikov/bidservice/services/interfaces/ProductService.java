package ru.buzynnikov.bidservice.services.interfaces;


import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;

/**
 * Интерфейс службы продуктов (ProductService).
 * Определяет методы для работы с продуктами.
 */
public interface ProductService {
    /**
     * Возвращает список всех продуктов.
     *
     * @return Список всех продуктов
     */
    Iterable<Product> getAllProduct();

    /**
     * Сохраняет новый продукт.
     *
     * @param product DTO с информацией о продукте
     * @return Сохраненный продукт
     */
    Product saveProduct(ProductDTO product);


    /**
     * Обновляет информацию о продукте.
     *
     * @param id  Идентификатор продукта
     * @param dto DTO с обновлёнными данными о продукте
     * @return Обновлённый продукт
     */
    Product updateProduct(Long id, ProductDTO dto);

    /**
     * Удаляет продукт по идентификатору.
     *
     * @param id Идентификатор продукта
     * @return Идентификатор удалённого продукта
     */
    Long deleteProduct(Long id);

    /**
     * Поиск продукта по идентификатору.
     *
     * @param id Идентификатор продукта
     * @return Найденный продукт
     */
    Product findById(Long id);
}

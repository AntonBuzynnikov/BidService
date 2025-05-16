package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.exceptions.ProductNotFoundException;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.repositories.ProductRepository;
import ru.buzynnikov.bidservice.services.interfaces.ProductService;

import java.math.BigDecimal;
import java.util.Optional;


/**
 * Сервис для работы с продуктами.
 * Обеспечивает управление продуктами: добавление, редактирование, удаление и поиск.
 */
@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    /**
     * Возвращает список всех продуктов.
     *
     * @return Список всех продуктов
     */
    @Override
    public Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }
    /**
     * Добавляет новый продукт.
     *
     * @param product DTO с информацией о продукте
     * @return Добавленный продукт
     */
    @Override
    public Product saveProduct(ProductDTO product){
        return productRepository.save(createProduct(product));
    }
    /**
     * Обновляет информацию о продукте.
     *
     * @param id  Идентификатор продукта
     * @param dto DTO с новыми данными о продукте
     * @return Обновленный продукт
     */
    @Override
    public Product updateProduct(Long id, ProductDTO dto){
        Optional<Product> temp = productRepository.findById(id);
        if (temp.isPresent()){
            Product product = temp.get();
            product.setName(dto.getName());
            product.setPrice(BigDecimal.valueOf(dto.getPrice()));
            return productRepository.save(product);
        }
        throw new ProductNotFoundException("Продукт с id " + id + " не найден");
    }
    /**
     * Удаляет продукт по идентификатору.
     *
     * @param id Идентификатор продукта
     * @return ID удаленного продукта
     */
    @Override
    public Long deleteProduct(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return id;
        } else {
            throw new ProductNotFoundException("Продукт с id " + id + " не найден");
        }
    }
    /**
     * Создает объект продукта на основе переданного DTO.
     *
     * @param dto DTO с информацией о продукте
     * @return Новый экземпляр продукта
     */
    private Product createProduct(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setWeight(BigDecimal.valueOf(dto.getWeight()));
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        return product;
    }
    /**
     * Поиск продукта по идентификатору.
     *
     * @param id Идентификатор продукта
     * @return Найденный продукт
     */
    @Override
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }
}

package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.buzynnikov.bidservice.aspect.ToLog;
import ru.buzynnikov.bidservice.dto.ProductDTO;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.interfaces.ProductService;


/**
 * Контроллер для управления товарами (продуктами).
 * Поддерживает получение списка товаров, сохранение новых товаров, удаление существующих и обновление данных о товаре.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    /**
     * Возвращает полный список всех зарегистрированных товаров.
     *
     * @return Список всех товаров с HTTP-статусом OK (200)
     */
    @ToLog
    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProduct(){
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    /**
     * Сохраняет новый товар.
     * Требуемые поля в запросе:
     * - name (название товара)
     * - weight (вес товара)
     * - price (цена товара)
     *
     * Пример запроса:
     * {
     *     "name":"Бекон",
     *     "weight":1.0,
     *     "price":650
     * }
     *
     * @param product Объект DTO с информацией о новом товаре
     * @return Новый сохранённый товар с HTTP-статусом CREATED (201)
     */
    @ToLog
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product){
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.CREATED);
    }

    /**
     * Удаляет существующий товар по заданному идентификатору.
     *
     * @param id Идентификатор удаляемого товара
     * @return Успешность удаления с HTTP-статусом OK (200)
     */
    @ToLog
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.OK);
    }

    /**
     * Обновляет информацию о существующем товаре.
     * Для обновления требуется передать обновленные данные в теле запроса и указать идентификатор товара.
     *
     * Пример запроса:
     * {
     *     "name":"Бекон",
     *     "weight":1.0,
     *     "price":560
     * }
     *
     * @param productDTO Объект DTO с обновленными данными о товаре
     * @param id         Идентификатор обновляемого товара
     * @return Обновленный товар с HTTP-статусом OK (200)
     */
    @ToLog
    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        return new ResponseEntity<>(service.updateProduct(id,productDTO),HttpStatus.OK);
    }
}

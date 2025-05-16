package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.buzynnikov.bidservice.aspect.ToLog;
import ru.buzynnikov.bidservice.models.Bid;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.services.interfaces.BidService;

import java.util.HashMap;


/**
 * Контроллер для управления заявками (бидами).
 * Обрабатывает запросы, связанные с отправкой и получением заявок.
 */

@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;


    /**
     * Отправляет заявку и возвращает карту продуктов с их количеством.
     * Запись операции фиксируется в логах благодаря аннотации @ToLog.
     *
     * @return Ответ сервера с картой продуктов и статусом CREATED (201).
     */
    @ToLog
    @GetMapping("/send")
    public ResponseEntity<HashMap<Product,Double>> sendBid(){
        return new ResponseEntity<>(bidService.sendBid(), HttpStatus.CREATED);
    }

    /**
     * Получает заявку по её идентификатору.
     * Запись операции фиксируется в логах благодаря аннотации @ToLog.
     *
     * @param id Идентификатор заявки.
     * @return Ответ сервера с объектом заявки и статусом OK (200).
     */
    @ToLog
    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBid(@PathVariable Long id){
        return new ResponseEntity<>(bidService.getBidById(id),HttpStatus.OK);
    }
}

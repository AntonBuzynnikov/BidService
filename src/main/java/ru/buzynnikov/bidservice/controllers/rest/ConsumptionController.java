package ru.buzynnikov.bidservice.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.buzynnikov.bidservice.aspect.ToLog;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.services.interfaces.ConsumptionService;

import java.util.List;

/**
 * Контроллер для управления расходами (consumptions).
 * Предоставляет возможность отправки запросов на добавление расходов.
 */
@RestController
@RequestMapping("/consumption")
@RequiredArgsConstructor
public class ConsumptionController {
    private final ConsumptionService service;


    /**
     * Добавляет список расходов.
     * Данные отправляются в теле запроса в формате списка объектов типа Consumption.
     * Поле idProduct должно соответствовать полю id модели Product, иначе данные игнорируются.
     *
     * Пример тела запроса:
     * [
     *     {"idProduct":0000,"consumption":10},
     *     {"idProduct":0001,"consumption":15}
     * ]
     *
     * @param consumptionList Список расходов для добавления
     * @return Список успешно добавленных расходов с HTTP-статусом OK (200)
     */
    @ToLog
    @PostMapping("/add")
    public ResponseEntity<List<Consumption>> addAmtConsumption(@RequestBody List<Consumption> consumptionList){
        return new ResponseEntity<>(service.addAmtConsumption(consumptionList), HttpStatus.OK);
    }
}

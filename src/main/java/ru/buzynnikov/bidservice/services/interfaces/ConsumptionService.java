package ru.buzynnikov.bidservice.services.interfaces;

import ru.buzynnikov.bidservice.models.Consumption;

import java.util.List;

/**
 * Интерфейс службы потребления (ConsumptionService).
 * Определяет методы для работы с расходом продукции.
 */
public interface ConsumptionService {

    /**
     * Возвращает список всех зарегистрированных расходов.
     *
     * @return Список расходов
     */
    List<Consumption> getConsumption();

    /**
     * Добавляет новый расход или обновляет существующий.
     *
     * @param consumptions Список расходов для добавления или обновления
     * @return Список обновленных расходов
     */
    List<Consumption> addAmtConsumption(List<Consumption> consumptions);

    /**
     * Удаляет все зарегистрированные расходы.
     */
    void deleteAllConsumptions();
}

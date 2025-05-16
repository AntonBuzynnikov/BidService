package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.repositories.ConsumptionRepository;
import ru.buzynnikov.bidservice.services.interfaces.ConsumptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для обработки расхода продуктов.
 * Управляет хранением и обновлением информации о потреблении продуктов.
 */
@Service
@RequiredArgsConstructor
public class DefaultConsumptionService implements ConsumptionService {
    private final ConsumptionRepository repository;

    /**
     * Возвращает список текущих расходов продуктов.
     *
     * @return Список расходов продуктов
     */
    @Override
    public List<Consumption> getConsumption(){
        return repository.findAll();
    }


    /**
     * Добавляет новые расходы или обновляет существующие.
     *
     * @param consumptions Список расходов для добавления или обновления
     * @return Список обновленных расходов
     */
    @Override
    public List<Consumption> addAmtConsumption(List<Consumption> consumptions){
        List<Consumption> consAdd = new ArrayList<>();
        consumptions.forEach(cons-> consAdd.add(updateCons(cons)));
        return consAdd;
    }



    /**
     * Удаляет все расходы из базы данных.
     * После этого можно сформировать новый расход.
     */
    @Override
    public void deleteAllConsumptions(){
        repository.deleteAll();
    }

    /**
     * Обновляет существующий расход или добавляет новый, если такой записи ещё нет.
     *
     * @param consumption Новый расход для обновления или добавления
     * @return Обновленная или вновь созданная запись расхода
     */
    private Consumption updateCons(Consumption consumption){
        Optional<Consumption> consFromDB = repository.findById(consumption.getIdProduct());
        if(consFromDB.isPresent()){
            Consumption consUpdate = consFromDB.get();
            consUpdate.setConsumption(consumption.getConsumption() + consUpdate.getConsumption());
            return repository.save(consUpdate);
        } else{
            return repository.save(consumption);
        }
    }
}

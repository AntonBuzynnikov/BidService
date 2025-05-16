package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.exceptions.BidNotFoundException;
import ru.buzynnikov.bidservice.models.Bid;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.models.Quantity;
import ru.buzynnikov.bidservice.repositories.BidRepository;
import ru.buzynnikov.bidservice.repositories.ProductRepository;
import ru.buzynnikov.bidservice.repositories.QuantityRepository;
import ru.buzynnikov.bidservice.services.interfaces.BidService;
import ru.buzynnikov.bidservice.services.interfaces.ConsumptionService;
import ru.buzynnikov.bidservice.services.interfaces.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с заявками (BidService).
 * Управляет созданием, поиском и обработкой заявок.
 */
@Service
@RequiredArgsConstructor
public class DefaultBidService implements BidService {
    //Коэффициент,увеличивающий количество продуктов, относительно расхода за прошедший период
    private final String FACTOR = "1.2";
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final QuantityRepository quantityRepository;
    private final ConsumptionService consumptionService;

    /**
     * Формирование и возврат полной заявки.
     * Создается новая заявка, рассчитывается необходимое количество продукции,
     * формируется итоговая сумма и очищаются предыдущие расходы.
     *
     * @return Map с продукцией и необходимым количеством
     */
    @Override
    public HashMap<Product, Double> sendBid(){
        Bid bidInfo = bidRepository.save(createBid());
        HashMap<Product, Double> bid = new HashMap<>();
        List<Quantity> quantities = createListQuantity(consumptionService.getConsumption(), bidInfo.getId());
        BigDecimal sum = getSum(quantities);
        bidInfo.setSum(sum);
        bidRepository.save(bidInfo);
        for (Quantity quantity: quantities){
            bid.put(productService.findById(quantity.getProductId()),quantity.getQuantity());
        }
        consumptionService.deleteAllConsumptions();
        return bid;
    }

    /**
     * Поиск заявки по идентификатору.
     *
     * @param id Идентификатор заявки
     * @return Найденная заявка или выброшено исключение, если заявка не найдена
     */
    @Override
    public Bid getBidById(Long id){
        Optional<Bid> bid = bidRepository.findById(id);
        return bid.orElseThrow(()-> new BidNotFoundException("Заявка не найдена"));
    }

    /**
     * Создание новой заявки.
     *
     * @return Новая заявка с установленным временем создания
     */
    private Bid createBid(){
        Bid bid = new Bid();
        bid.setTimeCreate(LocalDateTime.now());
        return bid;
    }

    /**
     * Рассчитывает необходимое количество продукции, учитывая предыдущий расход и коэффициент.
     *
     * @param weight Вес единицы продукции
     * @param consumption Уровень предыдущего расхода
     * @return Необходимое количество продукции
     */
    private Double setQuantity(BigDecimal weight, Double consumption){
        double quantity = consumption * Double.parseDouble(FACTOR);
        if(weight.doubleValue() != 1) return Math.ceil(quantity/weight.doubleValue());
        return  Math.ceil(quantity);
    }

    /**
     * Формирует список количеств продукции для конкретной заявки.
     *
     * @param consumptions Список расходов предыдущей поставки
     * @param bidId Идентификатор заявки
     * @return Список количеств для размещения заказа
     */
    private List<Quantity> createListQuantity(List<Consumption> consumptions,Long bidId){
        List<Quantity> quantities = new ArrayList<>();
        for (Consumption product: consumptions){
            Optional<Product> optional = productRepository.findById(product.getIdProduct());
            if(optional.isPresent()){
                Product newProduct = optional.get();
                quantities.add(createQuantity(newProduct.getId(),
                        setQuantity(newProduct.getWeight(),product.getConsumption()),
                        bidId));
            }
        }
        return quantities;
    }

    /**
     * Создает запись количества для определенного продукта в рамках заявки.
     *
     * @param productId Идентификатор продукта
     * @param quantity Необходимое количество
     * @param bidId Идентификатор заявки
     * @return Новое количество продукции
     */
    private Quantity createQuantity(Long productId, Double quantity, Long bidId){
        Quantity quant = new Quantity();
        quant.setQuantity(quantity);
        quant.setProductId(productId);
        quant.setBidId(bidId);
        return quantityRepository.save(quant);
    }

    /**
     * Вычисляет общую сумму заявки, умножая цену каждого продукта на его количество.
     *
     * @param quantities Список необходимых количеств
     * @return Итоговая сумма заявки
     */
    private BigDecimal getSum(List<Quantity> quantities){
        BigDecimal sum = new BigDecimal(0);
        for (Quantity quantity : quantities) {
            Optional<Product> product = productRepository.findById(quantity.getProductId());
            if (product.isPresent()){
                sum = sum.add(product.get().getPrice().multiply(BigDecimal.valueOf(quantity.getQuantity())));
            }
        }
        return sum;
    }

}

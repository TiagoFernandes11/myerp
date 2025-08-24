package br.erp.myerp.domain.order.service;

import br.erp.myerp.domain.order.client.StockClient;
import br.erp.myerp.domain.order.client.StockMovementClient;
import br.erp.myerp.domain.order.client.StockMovementItemClient;
import br.erp.myerp.domain.order.dto.order.OrderCreateDTO;
import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.dto.order.OrderUpdateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementUpdateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.order.entity.Order;
import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.order.enums.OrderStatus;
import br.erp.myerp.domain.order.mapper.OrderMapper;
import br.erp.myerp.domain.order.mapper.StockMovementItemMapperForOrder;
import br.erp.myerp.domain.order.repository.OrderRepository;
import br.erp.myerp.domain.stock.entity.StockMovement;
import br.erp.myerp.domain.stock.entity.StockMovementItem;
import br.erp.myerp.domain.stock.enums.MovementType;
import br.erp.myerp.domain.stock.mapper.StockMovementItemMapper;
import br.erp.myerp.domain.stock.mapper.StockMovementMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private StockClient stockClient;

    @Autowired
    private StockMovementClient stockMovementClient;

    @Autowired
    private StockMovementItemClient stockMovementItemClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StockMovementItemMapperForOrder stockMovementItemMapper;

    public List<OrderResponseDTO> findAll(int pageSize, int pageNum){
        return orderRepository.findAll(PageRequest.of(pageNum, pageSize)).stream().map(order -> orderMapper.toOrderResponseDTO(order)).toList();
    }

    public OrderResponseDTO findById(long id){
        return orderMapper.toOrderResponseDTO(orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not founded with id: " + id)));
    }

    public void createOrder(OrderCreateDTO orderCreateDTO){
        if(!stockClient.checkStock(orderCreateDTO.getOrderItems())){
            throw new IllegalArgumentException("The stock does not have sufficient quantity for this sale");
        }

        StockMovementCreateDTO stockMovement = new StockMovementCreateDTO();
        stockMovement.setType(MovementType.OUT);
        stockMovement.setDescription("Sale for client: " + orderCreateDTO.getClientId());

        List<StockMovementItemResponseDTO> stockItens = new ArrayList<>();

        for(OrderItem item : orderCreateDTO.getOrderItems()){
            StockMovementItemCreateDTO stockItem = new StockMovementItemCreateDTO();
            stockItem.setProductId(item.getProductId());
            stockItem.setQuantity(item.getQuantity());
            StockMovementItemResponseDTO st = stockMovementItemClient.save(stockItem);
            stockItens.add(st);
        }

        stockMovement.setItems(stockItens);

        StockMovementResponseDTO stockMovementResponseDTO = stockMovementClient.create(stockMovement);

        orderCreateDTO.setStockMovementId(stockMovementResponseDTO.getId());
        orderCreateDTO.setStatus(OrderStatus.PENDING_PAYMENT);
        Order order = orderMapper.toOrder(orderCreateDTO);
        orderRepository.save(order);
    }

    public void updateOrder(OrderUpdateDTO orderUpdateDTO){
        StockMovementResponseDTO stockMovement = stockMovementClient.get(orderUpdateDTO.getStockMovementId());
        StockMovementUpdateDTO stockMovementUpdateDTO = new StockMovementUpdateDTO();

        stockMovementUpdateDTO.setId(stockMovement.getId());
        stockMovementUpdateDTO.setDescription(stockMovement.getDescription());
        stockMovementUpdateDTO.setType(MovementType.OUT);

        List<StockMovementItem> items = new ArrayList<>();

        orderUpdateDTO.getItems().forEach(item -> {
            if(item.getQuantity() < 0){
                throw new IllegalArgumentException("product quantity must be positive");
            }
            StockMovementItemResponseDTO newItem = new StockMovementItemResponseDTO();
            for(StockMovementItemResponseDTO stockMovementItem : stockMovement.getItems()){
                if(item.getProductId() == stockMovementItem.getProductId()){
                    newItem.setId(stockMovementItem.getId());
                    newItem.setQuantity(item.getQuantity());
                    newItem.setProductId(item.getProductId());
                    newItem.setStockMovement(stockMovementItem.getStockMovement());
                    items.add(stockMovementItemMapper.toStockMovementItem(newItem));
                }
            }
        });

        stockMovementUpdateDTO.setItems(items);
        StockMovementUpdateDTO answer = stockMovementClient.update(stockMovementUpdateDTO);

        if(answer == null){
            return;
        }

        orderRepository.save(orderMapper.toOrder(orderUpdateDTO));
    }
}

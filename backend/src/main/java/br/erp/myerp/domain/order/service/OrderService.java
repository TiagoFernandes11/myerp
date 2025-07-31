package br.erp.myerp.domain.order.service;

import br.erp.myerp.domain.order.client.StockClient;
import br.erp.myerp.domain.order.client.StockMovementClient;
import br.erp.myerp.domain.order.client.StockMovementItemClient;
import br.erp.myerp.domain.order.dto.order.OrderCreateDTO;
import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.dto.order.OrderUpdateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.order.entity.Order;
import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.order.mapper.OrderMapper;
import br.erp.myerp.domain.order.repository.OrderRepository;
import br.erp.myerp.domain.stock.enums.MovementType;
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

    public List<OrderResponseDTO> findAll(int pageSize, int pageNum){
        return orderRepository.findAll(PageRequest.of(pageNum, pageSize)).stream().map(order -> orderMapper.toOrderResponseDTO(order)).toList();
    }

    public OrderResponseDTO findById(long id){
        return orderMapper.toOrderResponseDTO(orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not founded with id: " + id)));
    }

    public void createOrder(OrderCreateDTO orderCreateDTO){
        if(!stockClient.checkStock(orderCreateDTO.getOrderItems())){
            throw new IllegalArgumentException("The stock does not have sufficient quantity for a this sale");
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

        stockMovementClient.create(stockMovement);

        Order order = orderMapper.toOrder(orderCreateDTO);
        orderRepository.save(order);
    }

    public void updateOrder(OrderUpdateDTO orderUpdateDTO){

    }
}

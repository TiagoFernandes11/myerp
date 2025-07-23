package br.erp.myerp.domain.order.service;

import br.erp.myerp.domain.order.dto.order.OrderCreateDTO;
import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.order.mapper.OrderMapper;
import br.erp.myerp.domain.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderResponseDTO> findAll(int pageSize, int pageNum){
        return orderRepository.findAll(PageRequest.of(pageNum, pageSize)).stream().map(order -> orderMapper.toOrderResponseDTO(order)).toList();
    }

    public OrderResponseDTO findById(long id){
        return orderMapper.toOrderResponseDTO(orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not founded with id: " + id)));
    }

    public void createOrder(OrderCreateDTO orderCreateDTO){
        List<StockMovementItemCreateDTO> stockItens = new ArrayList<>();
        StockMovementCreateDTO stockMovementCreateDTO = new StockMovementCreateDTO();

        for(OrderItem item : orderCreateDTO.getOrderItems()){
            StockMovementItemCreateDTO stockItem = new StockMovementItemCreateDTO();
            stockItem.setProductId(item.getProductId());
            stockItem.setQuantity(item.getQuantity());
            stockItens.add(stockItem);
        }

        stockMovementCreateDTO.setItems(stockItens);

        //todo
    }
}

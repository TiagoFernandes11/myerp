package br.erp.myerp.domain.order.service;

import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.mapper.OrderMapper;
import br.erp.myerp.domain.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
}

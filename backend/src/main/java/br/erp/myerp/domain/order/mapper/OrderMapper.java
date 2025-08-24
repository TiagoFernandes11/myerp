package br.erp.myerp.domain.order.mapper;

import br.erp.myerp.domain.order.dto.order.OrderCreateDTO;
import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.dto.order.OrderUpdateDTO;
import br.erp.myerp.domain.order.dto.orderItem.OrderItemResponseDTO;
import br.erp.myerp.domain.order.entity.Order;
import br.erp.myerp.domain.order.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderCreateDTO orderCreateDTO);

    Order toOrder(OrderUpdateDTO orderUpdateDTO);

    OrderItemResponseDTO map(OrderItem value);

    OrderResponseDTO toOrderResponseDTO(Order order);
}

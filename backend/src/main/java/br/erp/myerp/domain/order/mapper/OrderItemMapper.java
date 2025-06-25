package br.erp.myerp.domain.order.mapper;

import br.erp.myerp.domain.order.dto.orderItem.OrderItemResponseDTO;
import br.erp.myerp.domain.order.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem);
}

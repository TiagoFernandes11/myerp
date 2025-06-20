package br.erp.myerp.domain.order.mapper;

import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO toOrderResponseDTO(Order order);
}

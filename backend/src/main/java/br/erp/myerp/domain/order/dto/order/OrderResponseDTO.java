package br.erp.myerp.domain.order.dto.order;

import br.erp.myerp.domain.order.dto.orderItem.OrderItemResponseDTO;
import br.erp.myerp.domain.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private long id;

    private long clientId;

    private OrderStatus status;

    private Long stockMovementId;

    private List<OrderItemResponseDTO> itens;

    private BigDecimal total;
}

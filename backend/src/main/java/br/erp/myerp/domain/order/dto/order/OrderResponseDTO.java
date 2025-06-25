package br.erp.myerp.domain.order.dto.order;

import br.erp.myerp.domain.order.dto.orderItem.OrderItemResponseDTO;
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

    private List<OrderItemResponseDTO> itens;

    private BigDecimal total;
}

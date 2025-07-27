package br.erp.myerp.domain.stock.dto.order;

import br.erp.myerp.domain.order.entity.Order;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemCreateDTO {

    private Long productId;

    private int quantity;
}

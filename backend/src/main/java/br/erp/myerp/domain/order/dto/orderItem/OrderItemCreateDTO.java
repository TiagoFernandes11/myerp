package br.erp.myerp.domain.order.dto.orderItem;

import br.erp.myerp.domain.order.entity.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemCreateDTO {

    @NotNull(message = "Order must no be null")
    private Order order;

    @NotNull(message = "Product can not be null")
    private Long productId;

    @Positive(message = "Quantity must be positive")
    private int quantity;
}

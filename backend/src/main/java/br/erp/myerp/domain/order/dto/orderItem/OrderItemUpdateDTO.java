package br.erp.myerp.domain.order.dto.orderItem;

import br.erp.myerp.domain.order.entity.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemUpdateDTO {

    private Long id;

    private Order order;

    private Long productId;

    private int quantity;
}

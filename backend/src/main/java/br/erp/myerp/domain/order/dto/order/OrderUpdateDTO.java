package br.erp.myerp.domain.order.dto.order;

import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.order.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDTO {

    @NotNull(message = "Id must not be null")
    private long id;

    @NotBlank(message = "client id must not be blank")
    @NotNull(message = "client id must not be null")
    private long clientId;

    private Long stockMovementId;

    @NotBlank(message = "status must not be blank")
    @NotNull(message = "status must not be null")
    private OrderStatus status;

    @NotEmpty(message = "Products id can not be empty")
    private List<OrderItem> items;

    @Positive(message = "Total must be positive")
    private BigDecimal total;
}

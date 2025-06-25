package br.erp.myerp.domain.order.entity;

import br.erp.myerp.domain.order.dto.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @NotNull(message = "Product can not be null")
    private Long productId;

    @Positive(message = "Quantity must be positive")
    private int quantity;
}

package br.erp.myerp.domain.order.entity;

import br.erp.myerp.domain.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long clientId;

    private OrderStatus status;

    private Long stockMovementId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> itens;

    private BigDecimal total;
}

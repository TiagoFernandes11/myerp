package br.erp.myerp.domain.shoppingcart.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.generator.EventType.INSERT;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> itens;

    private BigDecimal total;

    @CurrentTimestamp(event = INSERT)
    private LocalDateTime createdAt;

    private LocalDateTime finalizedAt;
}

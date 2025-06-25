package br.erp.myerp.domain.shoppingcart.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> itens;

    private BigDecimal total;

    private LocalDateTime createdAt;

    private LocalDateTime finalizedAt;
}

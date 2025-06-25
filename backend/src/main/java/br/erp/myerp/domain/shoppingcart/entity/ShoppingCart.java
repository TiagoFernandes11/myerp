package br.erp.myerp.domain.shoppingcart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<ShoppingCartItem> itens;

    private BigDecimal total;

    private LocalDateTime createdAt;

    private LocalDateTime finalizedAt;
}

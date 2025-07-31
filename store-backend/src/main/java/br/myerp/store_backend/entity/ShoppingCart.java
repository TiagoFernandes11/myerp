package br.myerp.store_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.generator.EventType.INSERT;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientIdErp;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> itens;

    private BigDecimal total;

    @CurrentTimestamp(event = INSERT)
    private LocalDateTime createdAt;

    private LocalDateTime finalizedAt;
}

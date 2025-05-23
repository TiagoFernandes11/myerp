package br.erp.myerp.backend.stock.entity;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.enums.MovemenType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovemenType type;

    @Positive
    private Integer quantity;

    private String description;

    private LocalDateTime timestamp = LocalDateTime.now();
}

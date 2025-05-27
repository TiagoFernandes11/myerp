package br.erp.myerp.backend.stock.entity;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.enums.MovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Stock stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String description;

    private LocalDateTime timestamp;

    public StockMovement() {
        this.timestamp = LocalDateTime.now();
    }

    public StockMovement(Long id, Stock stock, MovementType type, Integer quantity, String description) {
        this.id = id;
        this.stock = stock;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}

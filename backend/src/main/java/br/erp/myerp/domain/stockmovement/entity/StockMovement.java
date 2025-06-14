package br.erp.myerp.domain.stockmovement.entity;

import br.erp.myerp.domain.stockmovement.enums.MovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockId;

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

    public StockMovement(Long id, Long stockId, MovementType type, Integer quantity, String description) {
        this.id = id;
        this.stockId = stockId;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}

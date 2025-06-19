package br.erp.myerp.domain.stock.entity;

import br.erp.myerp.domain.stock.enums.MovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<StockMovementItem> items;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private String description;

    private LocalDateTime timestamp;

    public StockMovement() {
        this.timestamp = LocalDateTime.now();
    }

    public StockMovement(Long id, List<StockMovementItem> productsId, MovementType type, String description) {
        this.id = id;
        this.items = productsId;
        this.type = type;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}

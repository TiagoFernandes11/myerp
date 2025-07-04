package br.erp.myerp.domain.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StockMovementItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productId;
    private int quantity;

    @JsonIgnore
    @ManyToOne
    private StockMovement stockMovement;

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

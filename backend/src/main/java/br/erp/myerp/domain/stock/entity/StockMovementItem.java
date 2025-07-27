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

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

package br.erp.myerp.backend.domain.stockmovement.dto.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class StockDTO {

    private Long id;

    private Long productId;

    private int quantity;

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

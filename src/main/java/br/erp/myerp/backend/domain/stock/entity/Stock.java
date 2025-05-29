package br.erp.myerp.backend.domain.stock.entity;

import br.erp.myerp.backend.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantity;

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

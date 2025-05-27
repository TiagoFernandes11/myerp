package br.erp.myerp.backend.cruds.stock.entity;

import br.erp.myerp.backend.cruds.product.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private int quantity;

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

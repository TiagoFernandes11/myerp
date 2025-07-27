package br.erp.myerp.domain.shoppingcart.entity;

import br.erp.myerp.domain.shoppingcart.dto.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int productId;

    private int quantity;

    private BigDecimal total;
}

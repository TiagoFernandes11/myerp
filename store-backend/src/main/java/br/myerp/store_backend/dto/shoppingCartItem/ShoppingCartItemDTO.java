package br.myerp.store_backend.dto.shoppingCartItem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartItemDTO {

    private Long productId;

    private String name;

    private Long clientIdErp;

    private int quantity;

    private BigDecimal total;
}

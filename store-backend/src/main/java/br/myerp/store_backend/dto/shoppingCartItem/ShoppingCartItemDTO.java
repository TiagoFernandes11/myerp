package br.myerp.store_backend.dto.shoppingCartItem;

import lombok.Data;

@Data
public class ShoppingCartItemDTO {

    private Long productId;

    private Long clientIdErp;
}

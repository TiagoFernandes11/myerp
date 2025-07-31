package br.myerp.store_backend.dto.shoppingCart;

import br.myerp.store_backend.entity.ShoppingCartItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoppingCartResponseDTO {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> itens;

    private BigDecimal total;
}

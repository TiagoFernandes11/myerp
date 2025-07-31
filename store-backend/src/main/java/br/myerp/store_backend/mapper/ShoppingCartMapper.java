package br.myerp.store_backend.mapper;

import br.myerp.store_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import br.myerp.store_backend.entity.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {
    ShoppingCartResponseDTO toResponseDTO(ShoppingCart shoppingCart);
}

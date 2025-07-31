package br.myerp.store_backend.service;

import br.myerp.store_backend.client.ProductClient;
import br.myerp.store_backend.dto.product.Product;
import br.myerp.store_backend.dto.shoppingCart.ShoppingCartCreateDTO;
import br.myerp.store_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import br.myerp.store_backend.dto.shoppingCartItem.ShoppingCartItemDTO;
import br.myerp.store_backend.entity.ShoppingCart;
import br.myerp.store_backend.entity.ShoppingCartItem;
import br.myerp.store_backend.mapper.ShoppingCartMapper;
import br.myerp.store_backend.repository.ShoppingCartItemRepository;
import br.myerp.store_backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShoppingCartServices {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartMapper mapper;

    public ShoppingCart getExistingShoppingCart(Long clientIdErp){
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByClientIdErp(clientIdErp);
        return shoppingCart.orElseGet(() -> createCart(clientIdErp));
    }

    public ShoppingCartResponseDTO getCart(Long clientIdErp){
        return mapper.toResponseDTO(getExistingShoppingCart(clientIdErp));
    }

    public ShoppingCart createCart(Long clientIdErp){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(null);
        shoppingCart.setClientIdErp(clientIdErp);
        shoppingCart.setCreatedAt(LocalDateTime.now());

        return shoppingCartRepository.save(shoppingCart);
    }

    public void addProduct(ShoppingCartItemDTO shoppingCartItemDTO){
        ShoppingCart existingShoppingCart = getExistingShoppingCart(shoppingCartItemDTO.getClientIdErp());

        for (ShoppingCartItem item : existingShoppingCart.getItens()) {
            if (item.getProductId().equals(shoppingCartItemDTO.getProductId())) {
                item.setQuantity(item.getQuantity() + 1);
                shoppingCartItemRepository.save(item);
                shoppingCartRepository.save(existingShoppingCart);
                return;
            }
        }

        Product product = productClient.get(shoppingCartItemDTO.getProductId());

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProductId(shoppingCartItemDTO.getProductId());
        shoppingCartItem.setQuantity(1);
        shoppingCartItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getQuantity())));

        shoppingCartItemRepository.save(shoppingCartItem);

        existingShoppingCart.getItens().add(shoppingCartItem);
        shoppingCartRepository.save(existingShoppingCart);
    }
}

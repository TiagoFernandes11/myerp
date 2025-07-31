package br.myerp.store_backend.service;

import br.myerp.store_backend.client.ClientClient;
import br.myerp.store_backend.client.ProductClient;
import br.myerp.store_backend.dto.client.ClientResponseDTO;
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

import javax.security.auth.login.CredentialException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShoppingCartServices {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ClientClient clientClient;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartMapper mapper;

    private ShoppingCart getExistingShoppingCart(Long clientIdErp){
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

    public void addProduct(ShoppingCartItemDTO shoppingCartItemDTO, String email){
        ClientResponseDTO existingClient = clientClient.get(shoppingCartItemDTO.getClientIdErp());
        if(!email.equals(existingClient.getEmail())){
            throw new RuntimeException("User not allowed");
        }

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

    public void removeProduct(ShoppingCartItemDTO item, String email){
        ClientResponseDTO existingClient = clientClient.get(item.getClientIdErp());
        if(!email.equals(existingClient.getEmail())){
            throw new RuntimeException("User not allowed");
        }

        ShoppingCart existingShoppingCart = getExistingShoppingCart(item.getClientIdErp());

        for (ShoppingCartItem i : existingShoppingCart.getItens()) {
            if (i.getProductId().equals(item.getProductId()) && i.getQuantity() > 1) {
                i.setQuantity(i.getQuantity() - 1);
                shoppingCartItemRepository.save(i);
                shoppingCartRepository.save(existingShoppingCart);
                return;
            } else {
                existingShoppingCart.getItens().remove(i);
                shoppingCartItemRepository.delete(i);
                shoppingCartRepository.save(existingShoppingCart);
                return;
            }
        }
    }
}

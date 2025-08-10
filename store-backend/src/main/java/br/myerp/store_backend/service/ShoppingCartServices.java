package br.myerp.store_backend.service;

import br.myerp.store_backend.client.ClientClient;
import br.myerp.store_backend.client.ProductClient;
import br.myerp.store_backend.dto.client.ClientResponseDTO;
import br.myerp.store_backend.dto.product.Product;
import br.myerp.store_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import br.myerp.store_backend.dto.shoppingCartItem.ShoppingCartItemDTO;
import br.myerp.store_backend.entity.ShoppingCart;
import br.myerp.store_backend.entity.ShoppingCartItem;
import br.myerp.store_backend.mapper.ShoppingCartMapper;
import br.myerp.store_backend.repository.ShoppingCartItemRepository;
import br.myerp.store_backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        try{
            ClientResponseDTO existingClient = clientClient.get(clientIdErp);
        }
        catch (HttpClientErrorException.NotFound e){
            return null;
        }
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
        ClientResponseDTO existingClient = null;

        try{
            existingClient = clientClient.get(shoppingCartItemDTO.getClientIdErp());
        }
        catch (HttpClientErrorException.NotFound e){
            return;
        }

        if(existingClient != null && !email.equals(existingClient.getEmail())){
            throw new RuntimeException("User not allowed");
        }

        Product product = productClient.get(shoppingCartItemDTO.getProductId());

        ShoppingCart existingShoppingCart = getExistingShoppingCart(shoppingCartItemDTO.getClientIdErp());

        for (ShoppingCartItem item : existingShoppingCart.getItens()) {
            if (item.getProductId().equals(shoppingCartItemDTO.getProductId())) {
                item.setProductId(shoppingCartItemDTO.getProductId());
                item.setName(shoppingCartItemDTO.getName());
                item.setQuantity(item.getQuantity() + shoppingCartItemDTO.getQuantity());
                item.setTotal(item.getTotal().add(product.getPrice().multiply(BigDecimal.valueOf(shoppingCartItemDTO.getQuantity()))));
                shoppingCartItemRepository.save(item);
                existingShoppingCart.setTotal(existingShoppingCart.getTotal().add(product.getPrice().multiply(BigDecimal.valueOf(shoppingCartItemDTO.getQuantity()))));
                shoppingCartRepository.save(existingShoppingCart);
                return;
            }
        }

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setName(shoppingCartItemDTO.getName());
        shoppingCartItem.setProductId(shoppingCartItemDTO.getProductId());
        shoppingCartItem.setQuantity(shoppingCartItemDTO.getQuantity());
        shoppingCartItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getQuantity())));

        shoppingCartItemRepository.save(shoppingCartItem);

        existingShoppingCart.getItens().add(shoppingCartItem);
        existingShoppingCart.setTotal(calculateTotal(existingShoppingCart));
        shoppingCartRepository.save(existingShoppingCart);
    }

    public void removeProduct(ShoppingCartItemDTO item, String email){
        ClientResponseDTO existingClient = null;

        try{
            existingClient = clientClient.get(item.getClientIdErp());
        }
        catch (HttpClientErrorException.NotFound e){
            return;
        }

        if(existingClient != null && !email.equals(existingClient.getEmail())){
            throw new RuntimeException("User not allowed");
        }

        Product product = productClient.get(item.getProductId());
        ShoppingCart existingShoppingCart = getExistingShoppingCart(item.getClientIdErp());

        for (ShoppingCartItem i : existingShoppingCart.getItens()) {
            if (i.getProductId().equals(item.getProductId()) && item.getQuantity() <= i.getQuantity() && i.getQuantity() > 1) {
                i.setQuantity(i.getQuantity() - item.getQuantity());
                i.setTotal(product.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())));
                shoppingCartItemRepository.save(i);
            } else {
                existingShoppingCart.getItens().remove(i);
                shoppingCartItemRepository.delete(i);
            }
            existingShoppingCart.setTotal(calculateTotal(existingShoppingCart));
            shoppingCartRepository.save(existingShoppingCart);
            return;
        }
    }

    private BigDecimal calculateTotal(ShoppingCart shoppingCart){
        BigDecimal total = BigDecimal.valueOf(0d);
        for(ShoppingCartItem shoppingCartItem : shoppingCart.getItens()){
            total = total.add(shoppingCartItem.getTotal());
        }
        return total;
    }
}

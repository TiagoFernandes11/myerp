package br.myerp.store_backend.controller;

import br.myerp.store_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import br.myerp.store_backend.dto.shoppingCartItem.ShoppingCartItemDTO;
import br.myerp.store_backend.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServices shoppingCartServices;

    @GetMapping
    public ResponseEntity<ShoppingCartResponseDTO> getCart(@RequestParam Long clientIdErp){
        ShoppingCartResponseDTO response = shoppingCartServices.getCart(clientIdErp);
        if(response == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ShoppingCartItemDTO shoppingCartItem, Authentication authentication){
        shoppingCartServices.addProduct(shoppingCartItem, authentication.getName());
        return ResponseEntity.ok().body("Item added");
    }

    @PostMapping("/remove-product")
    public ResponseEntity<String> removeProduct(@RequestBody ShoppingCartItemDTO shoppingCartItem, Authentication authentication){
        shoppingCartServices.removeProduct(shoppingCartItem, authentication.getName());
        return ResponseEntity.ok().body("Item removed");
    }
}

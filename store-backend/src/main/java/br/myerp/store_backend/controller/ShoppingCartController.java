package br.myerp.store_backend.controller;

import br.myerp.store_backend.dto.shoppingCart.ShoppingCartResponseDTO;
import br.myerp.store_backend.dto.shoppingCartItem.ShoppingCartItemDTO;
import br.myerp.store_backend.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/api/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServices shoppingCartServices;

    @GetMapping
    public ResponseEntity<ShoppingCartResponseDTO> getCart(@RequestParam Long clientIdErp){
        ShoppingCartResponseDTO response = shoppingCartServices.getCart(clientIdErp);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody ShoppingCartItemDTO shoppingCartItem){
        shoppingCartServices.addProduct(shoppingCartItem);
        return ResponseEntity.ok().body("Item added");
    }
}

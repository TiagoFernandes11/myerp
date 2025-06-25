package br.erp.myerp.domain.shoppingcart.repository;

import br.erp.myerp.domain.shoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}

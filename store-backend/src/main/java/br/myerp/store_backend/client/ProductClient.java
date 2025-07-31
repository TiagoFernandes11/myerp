package br.myerp.store_backend.client;

import br.myerp.store_backend.dto.product.Product;

public interface ProductClient {
    Product get(Long productId);
}

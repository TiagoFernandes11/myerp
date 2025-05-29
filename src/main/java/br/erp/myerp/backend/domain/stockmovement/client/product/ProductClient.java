package br.erp.myerp.backend.domain.stockmovement.client.product;

import br.erp.myerp.backend.domain.stockmovement.dto.product.ProductDTO;

public interface ProductClient {

    ProductDTO getProduct(Long productId);
}

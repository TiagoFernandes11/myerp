package br.erp.myerp.domain.stockmovement.client.product;

import br.erp.myerp.domain.stockmovement.dto.product.ProductDTO;

public interface ProductClient {

    ProductDTO getProduct(Long productId);
}

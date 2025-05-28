package br.erp.myerp.backend.cruds.stockmovement.client.product;

import br.erp.myerp.backend.cruds.stockmovement.dto.product.ProductDTO;

public interface ProductClient {

    ProductDTO getProduct(Long productId);
}

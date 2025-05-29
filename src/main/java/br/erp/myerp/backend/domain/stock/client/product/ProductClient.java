package br.erp.myerp.backend.domain.stock.client.product;

import br.erp.myerp.backend.domain.stock.dto.product.ProductDTO;

public interface ProductClient {

    ProductDTO getProduct(Long id);
}

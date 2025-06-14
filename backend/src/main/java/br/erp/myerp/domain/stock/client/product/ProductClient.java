package br.erp.myerp.domain.stock.client.product;

import br.erp.myerp.domain.stock.dto.product.ProductDTO;

public interface ProductClient {

    ProductDTO getProduct(Long id);
}

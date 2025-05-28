package br.erp.myerp.backend.cruds.stock.client.product;

import br.erp.myerp.backend.cruds.stock.dto.product.ProductDTO;

public interface ProductClient {

    ProductDTO getProduct(Long id);
}

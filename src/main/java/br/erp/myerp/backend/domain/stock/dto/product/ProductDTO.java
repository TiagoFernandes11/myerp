package br.erp.myerp.backend.domain.stock.dto.product;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int quantity;
}

package br.myerp.store_backend.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;

    private String sku;

    private String name;

    private BigDecimal price;
}
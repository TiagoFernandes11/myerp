package br.erp.myerp.domain.shoppingcart.dto.product;

import lombok.Data;

@Data
public class Product {

    private Long id;

    private String sku;

    private String name;

    private String price;
}
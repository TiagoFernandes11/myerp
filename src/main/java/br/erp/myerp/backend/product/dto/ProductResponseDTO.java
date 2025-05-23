package br.erp.myerp.backend.product.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ProductResponseDTO {

    private Long id;

    @Column(unique = true, nullable = false)
    private String sku;

    private String name;

    private String formatedPrice;

    public ProductResponseDTO(Long id, String sku, String name, BigDecimal price) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.formatedPrice = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(price);;
    }

    public void setFormatedPrice(BigDecimal price) {
        this.formatedPrice = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(price);;
    }
}

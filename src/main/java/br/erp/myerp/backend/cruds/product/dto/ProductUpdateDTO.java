package br.erp.myerp.backend.cruds.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {

    @NotBlank(message = "SKU can not be blank")
    @Pattern(regexp = "[0-9]{14}", message = "SKU must be 14 digits long")
    private String sku;

    @NotBlank(message = "Product name can not be blank")
    private String name;

    @Positive(message = "Price must be positive")
    private BigDecimal price;
}

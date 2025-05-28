package br.erp.myerp.backend.cruds.stock.dto;

import br.erp.myerp.backend.cruds.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockUpdateDTO {

    @NotNull(message = "Product can not be null")
    private Product product;

    @NotNull(message = "Quantity can not be null")
    @Positive(message = "Quantity must be greater or equal than 0")
    private int quantity;
}

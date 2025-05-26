package br.erp.myerp.backend.stock.dto.stock;

import br.erp.myerp.backend.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    private Long id;

    @NotNull(message = "Product can not be null")
    private Product product;

    @NotNull(message = "Quantity can not be null")
    @NotBlank(message = "Quantity can not be blank")
    @Positive(message = "Quantity must be greater or equal than 0")
    private int quantity;
}

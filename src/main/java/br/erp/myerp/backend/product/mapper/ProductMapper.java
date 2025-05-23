package br.erp.myerp.backend.product.mapper;

import br.erp.myerp.backend.product.dto.ProductCreateDTO;
import br.erp.myerp.backend.product.dto.ProductResponseDTO;
import br.erp.myerp.backend.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductResponseDTO toResponseDTO(Product product);
}

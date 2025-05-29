package br.erp.myerp.backend.domain.product.mapper;

import br.erp.myerp.backend.domain.product.dto.ProductCreateDTO;
import br.erp.myerp.backend.domain.product.dto.ProductResponseDTO;
import br.erp.myerp.backend.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductResponseDTO toResponseDTO(Product product);
}

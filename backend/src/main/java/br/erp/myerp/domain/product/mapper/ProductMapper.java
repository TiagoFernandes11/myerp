package br.erp.myerp.domain.product.mapper;

import br.erp.myerp.domain.product.dto.ProductCreateDTO;
import br.erp.myerp.domain.product.dto.ProductResponseDTO;
import br.erp.myerp.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductResponseDTO toResponseDTO(Product product);
}

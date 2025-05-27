package br.erp.myerp.backend.cruds.product.mapper;

import br.erp.myerp.backend.cruds.product.dto.ProductCreateDTO;
import br.erp.myerp.backend.cruds.product.dto.ProductResponseDTO;
import br.erp.myerp.backend.cruds.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductResponseDTO toResponseDTO(Product product);
}

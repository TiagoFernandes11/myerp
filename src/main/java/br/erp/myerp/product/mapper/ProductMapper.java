package br.erp.myerp.product.mapper;

import br.erp.myerp.product.dto.ProductCreateDTO;
import br.erp.myerp.product.dto.ProductResponseDTO;
import br.erp.myerp.product.dto.ProductUpdateDTO;
import br.erp.myerp.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDTO dto);

    ProductResponseDTO toResponseDTO(Product product);
}

package br.erp.myerp.domain.product.service;

import br.erp.myerp.domain.product.dto.ProductCreateDTO;
import br.erp.myerp.domain.product.dto.ProductResponseDTO;
import br.erp.myerp.domain.product.dto.ProductUpdateDTO;
import br.erp.myerp.domain.product.entity.Product;
import br.erp.myerp.domain.product.mapper.ProductMapper;
import br.erp.myerp.domain.product.repository.ProductRepository;
import br.erp.myerp.domain.product.specification.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public Page<ProductResponseDTO> findAll(String filter, String value, int pageNum, int pageSize){
        Page<Product> products = productRepository.findAll(ProductSpecification.getProductSpecification(filter, value),PageRequest.of(pageNum, pageSize));
        return products.map((product -> productMapper.toResponseDTO(product)));
    }

    public ProductResponseDTO find(Long id){
        return productMapper.toResponseDTO(
                productRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Product with id: " + id + " was not found")
                )
        );
    }

    public ProductResponseDTO find(String sku){
        return productMapper.toResponseDTO(
                productRepository.findBySku(sku).orElseThrow(
                        () -> new EntityNotFoundException("Product with sku: " + sku + " was not found")
                )
        );

    }

    public void createProduct(ProductCreateDTO productCreateDTO){
        Product product = productMapper.toEntity(productCreateDTO);
        productRepository.save(product);
    }

    public void updateProduct(Long id, ProductUpdateDTO productUpdateDTO){
        Product product = new Product();
        product.setId(id);
        product.setSku(productUpdateDTO.getSku());
        product.setName(productUpdateDTO.getName());
        product.setPrice(productUpdateDTO.getPrice());
        product.setImage(productUpdateDTO.getImage());
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " was not found"));
        productRepository.delete(product);
    }
}

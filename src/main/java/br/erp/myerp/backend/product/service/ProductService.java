package br.erp.myerp.backend.product.service;

import br.erp.myerp.backend.product.dto.ProductCreateDTO;
import br.erp.myerp.backend.product.dto.ProductResponseDTO;
import br.erp.myerp.backend.product.dto.ProductUpdateDTO;
import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.product.mapper.ProductMapper;
import br.erp.myerp.backend.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

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
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " was not found"));
        productRepository.delete(product);
    }
}

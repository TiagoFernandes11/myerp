package br.erp.myerp.backend.cruds.product.repository;

import br.erp.myerp.backend.cruds.product.dto.ProductResponseDTO;
import br.erp.myerp.backend.cruds.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new br.erp.myerp.backend.cruds.product.dto.ProductResponseDTO(p.id, p.sku, p.name, p.price) FROM Product p")
    Optional<List<ProductResponseDTO>> findAllProducts();

    Optional<Product> findBySku(String sku);
}

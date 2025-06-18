package br.erp.myerp.domain.product.repository;

import br.erp.myerp.domain.product.dto.ProductResponseDTO;
import br.erp.myerp.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new br.erp.myerp.domain.product.dto.ProductResponseDTO(p.id, p.sku, p.name, p.price) FROM Product p LIMIT 20")
    Optional<List<ProductResponseDTO>> findAllProducts(Pageable pageable);

    Optional<Product> findBySku(String sku);
}

package br.erp.myerp.backend.stock.repository;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.dto.stock.StockDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT new br.erp.myerp.backend.stock.dto.stock.StockDTO(s.id, s.product, s.quantity) FROM Stock s")
    List<StockDTO> findAllDto();

    Optional<Stock> findByProduct(Product product);
}

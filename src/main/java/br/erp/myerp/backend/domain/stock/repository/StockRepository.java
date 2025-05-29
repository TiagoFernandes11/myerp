package br.erp.myerp.backend.domain.stock.repository;

import br.erp.myerp.backend.domain.stock.dto.StockResponseDTO;
import br.erp.myerp.backend.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT new br.erp.myerp.backend.domain.stock.dto.StockResponseDTO(s.id, s.productId, s.quantity) FROM Stock s")
    List<StockResponseDTO> findAllDto();

    @Query("SELECT new br.erp.myerp.backend.domain.stock.dto.StockResponseDTO(s.id, s.productId, s.quantity) FROM Stock s WHERE s.productId = :productId")
    Optional<StockResponseDTO> findByProductId(@Param("productId") Long productId);
}

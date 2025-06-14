package br.erp.myerp.domain.stockmovement.repository;

import br.erp.myerp.domain.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.domain.stockmovement.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    @Query("SELECT new br.erp.myerp.domain.stockmovement.dto.StockMovementResponseDTO(s.id, s.stockId, s.type, s.quantity, s.description, s.timestamp) FROM StockMovement s")
    List<StockMovementResponseDTO> findAllDto();

    Optional<StockMovement> findByStockId(Long stockId);
}

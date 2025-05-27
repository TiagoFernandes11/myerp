package br.erp.myerp.backend.stock.repository;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import br.erp.myerp.backend.stock.entity.StockMovement;
import br.erp.myerp.backend.stock.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    @Query("SELECT new br.erp.myerp.backend.stock.dto.stockMovement.StockMovement(s.stock, s.type, s.quantity, s.description, s.timestamp) FROM StockMovement s")
    List<StockMovementResponseDTO> findAllDto();

    Optional<StockMovement> findByStock(Stock stock);
}

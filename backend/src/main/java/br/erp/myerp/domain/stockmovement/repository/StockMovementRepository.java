package br.erp.myerp.domain.stockmovement.repository;

import br.erp.myerp.domain.stockmovement.dto.stockmovement.StockMovementResponseDTO;
import br.erp.myerp.domain.stockmovement.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}

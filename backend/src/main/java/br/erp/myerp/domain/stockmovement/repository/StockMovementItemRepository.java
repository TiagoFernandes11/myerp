package br.erp.myerp.domain.stockmovement.repository;

import br.erp.myerp.domain.stockmovement.entity.StockMovementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementItemRepository extends JpaRepository<StockMovementItem, Long> {
}

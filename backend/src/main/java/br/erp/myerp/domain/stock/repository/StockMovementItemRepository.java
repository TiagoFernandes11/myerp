package br.erp.myerp.domain.stock.repository;

import br.erp.myerp.domain.stock.entity.StockMovementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementItemRepository extends JpaRepository<StockMovementItem, Long>, JpaSpecificationExecutor<StockMovementItem> {
}

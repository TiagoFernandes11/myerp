package br.erp.myerp.domain.stock.specification;

import br.erp.myerp.domain.stock.entity.StockMovementItem;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class StockMovementItemSpecification {

    public static Specification<StockMovementItem> filter(String filter, String value){
        return ((root, query, criteriaBuilder) ->{
            if(filter == null || value == null || filter.isEmpty() || value.isEmpty()){
                return criteriaBuilder.conjunction();
            }

            List<String> allowerFields = List.of("todo");

            if(!allowerFields.contains(filter)){
                throw new IllegalArgumentException("Invalid filter");
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get(filter)), "%"+ value.toLowerCase() +"%");
        });
    }
}

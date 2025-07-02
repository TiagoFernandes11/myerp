package br.erp.myerp.domain.product.specification;

import br.erp.myerp.domain.product.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<Product> getProductSpecification(String filter, String value){
        return (root, query, criteriaBuilder) -> {
            if(filter == null|| value == null ||filter.isEmpty() || value.isEmpty()){
                return criteriaBuilder.conjunction();
            }

            List<String> allowedFields = List.of("id", "name", "sku", "price");

            if(!allowedFields.contains(filter)){
                throw new IllegalArgumentException("invalid filter");
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get(filter)), "%" + value.toLowerCase() + "%");
        };
    }
}

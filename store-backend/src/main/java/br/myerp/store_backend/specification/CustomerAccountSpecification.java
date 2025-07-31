package br.myerp.store_backend.specification;

import br.myerp.store_backend.entity.CustomerAccount;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CustomerAccountSpecification {

    public static Specification<CustomerAccount> genericFilter(String filter, String value) {
        return (root, query, criteriaBuilder) -> {
            if (filter == null || value == null || filter.isEmpty() || value.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            List<String> allowedFields = List.of("clientid", "name");

            if (!allowedFields.contains(filter)) {
                throw new IllegalArgumentException("invalid filter");
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get(filter)), "%" + value.toLowerCase() + "%");
        };

    }
}

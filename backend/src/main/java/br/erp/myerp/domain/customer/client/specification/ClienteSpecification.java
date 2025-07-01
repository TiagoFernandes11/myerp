package br.erp.myerp.domain.customer.client.specification;

import br.erp.myerp.domain.customer.client.entity.Client;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ClienteSpecification {

    public static Specification<Client> genericFilter(String filter, String value) {
        return (root, query, criteriaBuilder) -> {
            if (filter == null || value == null || filter.isEmpty() || value.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            List<String> allowedFields = List.of("email", "cpf", "cellphone");

            if (!allowedFields.contains(filter)) {
                throw new IllegalArgumentException("invalid filter");
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get(filter)), "%" + value.toLowerCase() + "%");
        };

    }
}

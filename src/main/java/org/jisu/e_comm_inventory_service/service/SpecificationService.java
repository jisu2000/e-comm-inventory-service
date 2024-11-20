package org.jisu.e_comm_inventory_service.service;

import org.jisu.e_comm_inventory_service.model.ProductEO;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class SpecificationService {

    public static Specification<ProductEO> getWithFilter(String category, Double lowPrice, Double highPrice) {

        return (root, query, criteriaBuilder) -> {

            Predicate predicate = (Predicate) criteriaBuilder.conjunction();

            if (category != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
            }

            if (lowPrice != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder
                                .greaterThanOrEqualTo(root.get("basePrice"), lowPrice));
            }

            if (highPrice != null) {

                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder
                                .lessThanOrEqualTo(root.get("basePrice"), highPrice));

            }

            return predicate;

        };
    }
}

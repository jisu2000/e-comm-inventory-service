package org.jisu.e_comm_inventory_service.repo;

import org.jisu.e_comm_inventory_service.model.ProductEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductRepo extends JpaRepository<ProductEO, Integer>,
        JpaSpecificationExecutor<ProductEO> {

        ProductEO findByProductId(String productId);
}

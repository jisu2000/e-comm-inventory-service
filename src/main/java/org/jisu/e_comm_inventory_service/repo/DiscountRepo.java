package org.jisu.e_comm_inventory_service.repo;

import org.jisu.e_comm_inventory_service.model.DiscountEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepo extends JpaRepository<DiscountEO,Integer> {
    List<DiscountEO> findByProductId(String productId);
}

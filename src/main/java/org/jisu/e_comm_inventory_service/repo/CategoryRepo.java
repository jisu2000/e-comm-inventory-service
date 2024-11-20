package org.jisu.e_comm_inventory_service.repo;

import org.jisu.e_comm_inventory_service.model.CategoryEO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<CategoryEO,Integer>{
    CategoryEO  findByName(String name);
}

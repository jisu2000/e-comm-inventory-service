package org.jisu.e_comm_inventory_service.service;

import java.util.List;

import org.jisu.e_comm_inventory_service.dto.request.CategoryRequest;
import org.jisu.e_comm_inventory_service.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest,String authHeader);
    List<CategoryResponse> getAllCategories();
}

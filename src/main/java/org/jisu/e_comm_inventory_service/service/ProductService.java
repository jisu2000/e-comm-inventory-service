package org.jisu.e_comm_inventory_service.service;

import org.jisu.e_comm_inventory_service.dto.request.ProductRequest;
import org.jisu.e_comm_inventory_service.dto.response.PaginatedResponse;
import org.jisu.e_comm_inventory_service.dto.response.ProductResponse;

import jakarta.validation.constraints.Min;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest, String authHeader);

    ProductResponse updateProduct(ProductRequest productRequest);

    PaginatedResponse getAllProducts(
            @Min(value = 0, message = "Page index should not go bellow 0") Integer pageNo,
            @Min(value = 1, message = "Page Size should not go bellow 1") Integer pageSize);

    PaginatedResponse applyFilter(
        @Min(value = 0, message = "Page index should not go bellow 0") Integer pageNo,
        @Min(value = 1, message = "Page Size should not go bellow 1") Integer pageSize,
        String sortBy,
        String sortDir,
        String category,
        Double upperBound,
        Double lowerBound
    );


    ProductResponse getProductByProductId(String productId);
}

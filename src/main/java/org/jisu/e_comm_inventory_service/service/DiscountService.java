package org.jisu.e_comm_inventory_service.service;

import org.jisu.e_comm_inventory_service.dto.request.DiscountRequest;
import org.jisu.e_comm_inventory_service.dto.response.ApiResponse;
import org.jisu.e_comm_inventory_service.dto.response.DiscountResponse;

import java.util.List;

public interface DiscountService {
    DiscountResponse addDiscount(String authHeader, DiscountRequest discountRequest);
    List<DiscountResponse> getAllDiscountOfAProduct(String productId);
    ApiResponse<?> deleteDiscount(String authHeader, Integer id);

}

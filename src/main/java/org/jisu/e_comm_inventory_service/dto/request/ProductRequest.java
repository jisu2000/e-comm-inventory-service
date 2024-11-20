package org.jisu.e_comm_inventory_service.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String productName;
    private double basePrice;
    private Integer stocks;
    private MultipartFile image;
    private String category;
}

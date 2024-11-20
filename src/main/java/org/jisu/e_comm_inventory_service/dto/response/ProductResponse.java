package org.jisu.e_comm_inventory_service.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
    private Integer id;
    private String productId;
    private String productName;
    private boolean isUnavaliable;
    private double basePrice;
    private Integer stocks;
    private String imageUrl;
    private String category;
    private double modifiedPrice;
}

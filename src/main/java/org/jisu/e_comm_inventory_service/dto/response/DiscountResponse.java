package org.jisu.e_comm_inventory_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiscountResponse {
    private Integer id;
    private Double discount;
    private String productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

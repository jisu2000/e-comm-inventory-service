package org.jisu.e_comm_inventory_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiscountRequest {
    @Min(value = 1, message = "Discount percentage should not be less than 1")
    @Max(value = 99,message = "Discount percentage should not go beyond 99")
    private Double discount;
    @NotNull(message = "ProductId can not be NULL")
    @NotBlank(message = "ProductId can not be empty")
    private String productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

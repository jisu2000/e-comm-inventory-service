package org.jisu.e_comm_inventory_service.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiscountEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double discount;
    private String productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

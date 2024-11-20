package org.jisu.e_comm_inventory_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String productId;
    private String productName;
    private boolean isUnavaliable;
    private double basePrice;
    private Integer stocks;
    private String imageUrl;
    private String category;
}

package org.jisu.e_comm_inventory_service.utils;

import lombok.RequiredArgsConstructor;
import org.jisu.e_comm_inventory_service.dto.response.ProductResponse;
import org.jisu.e_comm_inventory_service.model.DiscountEO;
import org.jisu.e_comm_inventory_service.repo.DiscountRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class ProductPriceModifierUtil {
    private final DiscountRepo discountRepo;

    public void modifyPrice(ProductResponse productResponse){

        Optional<DiscountEO> todayAffectingDiscount
                    = discountRepo.findByProductId(productResponse.getProductId())
                .stream()
                .filter(todayAffecting)
                .findFirst();




        productResponse.setModifiedPrice(
                todayAffectingDiscount.isPresent() ?
                        priceAfterPercentage(
                                productResponse.getBasePrice(),
                                todayAffectingDiscount.get().getDiscount())
                : productResponse.getBasePrice()
        );



    }

    private Predicate<DiscountEO> todayAffecting = new Predicate<DiscountEO>() {
        @Override
        public boolean test(DiscountEO discountEO) {
            LocalDateTime today = LocalDateTime.now();
            return
                    today.isAfter(discountEO.getStartDate())
                    &&
                    today.isBefore(discountEO.getEndDate());
        }
    };
    private double priceAfterPercentage(double basePrice, double percentage) {
        double price = basePrice - (basePrice * (percentage / 100));
        return Math.round(price * 100.0) / 100.0;
    }

}

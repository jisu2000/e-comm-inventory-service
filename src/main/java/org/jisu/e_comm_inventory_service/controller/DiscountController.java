package org.jisu.e_comm_inventory_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jisu.e_comm_inventory_service.dto.request.DiscountRequest;
import org.jisu.e_comm_inventory_service.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<?> addDiscount(
            @Valid @RequestBody DiscountRequest discountRequest,
            @RequestHeader("Authorization") String authHeader
    ){
        return new ResponseEntity<>(discountService.addDiscount(authHeader,discountRequest), HttpStatus.OK);
    }

    @GetMapping("/find-by-productId")
    public ResponseEntity<?> getAllDiscountOfProduct(
            @RequestParam("productId") String productId
    ){
        return new ResponseEntity<>(discountService.getAllDiscountOfAProduct(productId),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDiscount(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("discountId") Integer discountId
    ){
        return new ResponseEntity<>(discountService.deleteDiscount(authHeader,discountId),HttpStatus.OK);
    }
}

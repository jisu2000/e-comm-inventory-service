package org.jisu.e_comm_inventory_service.controller;

import org.jisu.e_comm_inventory_service.constant.PageConstants;
import org.jisu.e_comm_inventory_service.dto.request.ProductRequest;
import org.jisu.e_comm_inventory_service.dto.response.PaginatedResponse;
import org.jisu.e_comm_inventory_service.dto.response.ProductResponse;
import org.jisu.e_comm_inventory_service.service.ProductService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> saveProduct(
            @ModelAttribute ProductRequest productRequest,
            @RequestHeader("Authorization") String authHeader
            ) {

        return new ResponseEntity<>(
            productService.addProduct(
                productRequest,
                authHeader), 
            HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTheProducts(
          @RequestParam(
            value = "pageNo", 
            required = false, 
            defaultValue =  PageConstants.DEFAULT_PAGE_NO
            ) 
            Integer pageNo,
        
         @RequestParam(
            value = "pageSize", 
            required = false, 
            defaultValue = PageConstants.DEFAULT_PAGE_SIZE
            )
             Integer pageSize
    ){

        PaginatedResponse response = productService.getAllProducts(pageNo, pageSize);
        return new ResponseEntity<>(
            response,
            HttpStatus.OK);
    }


    @GetMapping("/filter")
    public ResponseEntity<?> filterProducts(
        @RequestParam(
            value = "pageNo", 
            required = false, 
            defaultValue =  PageConstants.DEFAULT_PAGE_NO
            ) 
            Integer pageNo,
        
         @RequestParam(
            value = "pageSize", 
            required = false, 
            defaultValue = PageConstants.DEFAULT_PAGE_SIZE
            )
             Integer pageSize,

        @RequestParam(
            value = "sortBy",
            required = false,
            defaultValue = PageConstants.DEFAULT_SORT_BY
            ) 
             String sortBy,
        @RequestParam(
            value = "sortDir",
            required = false,
            defaultValue = PageConstants.DEFAULT_SORT_DIR
            )
             String sortDir,
        @RequestParam(
            value = "category",
            required = false
        )
         String category,

        @RequestParam(
            value = "lowerPriceBound",
            required = false
        )
         Double lowerPriceLimit,
         
        @RequestParam(
            value = "upperPriceBound",
            required = false
        )
         Double upperPriceBound

    ){

        PaginatedResponse response = productService.applyFilter(pageNo, pageSize, sortBy, sortDir, category, lowerPriceLimit, upperPriceBound);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getProductByproductId(
        @RequestParam("productId") String productId
    ){
        ProductResponse response = productService.getProductByProductId(productId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

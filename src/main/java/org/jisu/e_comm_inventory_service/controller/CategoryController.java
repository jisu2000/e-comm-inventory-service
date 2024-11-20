package org.jisu.e_comm_inventory_service.controller;

import org.jisu.e_comm_inventory_service.dto.request.CategoryRequest;
import org.jisu.e_comm_inventory_service.service.CategoryService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest, @RequestHeader("Authorization") String authHeader){
        return new ResponseEntity<>(categoryService.addCategory(categoryRequest,authHeader),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> fetchAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

}

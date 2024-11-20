package org.jisu.e_comm_inventory_service.service.impl;

import java.util.*;

import org.jisu.e_comm_inventory_service.dto.request.*;
import org.jisu.e_comm_inventory_service.dto.response.*;
import org.jisu.e_comm_inventory_service.exception.*;
import org.jisu.e_comm_inventory_service.external.AuthService;
import org.jisu.e_comm_inventory_service.model.*;
import org.jisu.e_comm_inventory_service.repo.*;
import org.jisu.e_comm_inventory_service.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest,String authHeader) {

        UserResponse user = authService.getUserFromHeader(authHeader);

        boolean adminRolePresent = 
                            user.getRoles().stream()
                                .filter(e -> e.getRoleName().equals("ADMIN"))
                                .findAny().isPresent();

        if(!adminRolePresent){
            throw new AccessDeniedException("User have to be an ADMIN to add any Category");
        }

        CategoryEO goingToSaved = modelMapper.map(categoryRequest, CategoryEO.class);
        CategoryEO saved = categoryRepo.save(goingToSaved);
        CategoryResponse categoryResponse = modelMapper.map(saved, CategoryResponse.class);
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryEO> allCategories = categoryRepo.findAll();

        List<CategoryResponse> response = allCategories.stream()
                .map(e -> modelMapper.map(e, CategoryResponse.class))
                .toList();


        return response;
    }

}

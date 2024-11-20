package org.jisu.e_comm_inventory_service.service.impl;

import java.lang.module.ResolutionException;
import java.util.*;

import org.jisu.e_comm_inventory_service.constant.PageConstants;
import org.jisu.e_comm_inventory_service.dto.request.*;
import org.jisu.e_comm_inventory_service.dto.response.*;
import org.jisu.e_comm_inventory_service.exception.*;
import org.jisu.e_comm_inventory_service.external.*;
import org.jisu.e_comm_inventory_service.model.*;
import org.jisu.e_comm_inventory_service.repo.*;
import org.jisu.e_comm_inventory_service.service.*;
import org.jisu.e_comm_inventory_service.utils.ProductPriceModifierUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final ImageUploaderService imageUploaderService;
    private final AuthService authService;
    private final ProductPriceModifierUtil priceModifierUtil;

    @Value("${cloudinary.products.folder}")
    private String productFolder;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest, String authHeader) {

        UserResponse user = authService.getUserFromHeader(authHeader);

        boolean adminRolePresent = user.getRoles().stream()
                .filter(e -> e.getRoleName().equals("ADMIN"))
                .findAny().isPresent();

        if (!adminRolePresent) {
            throw new AccessDeniedException("User have to be an ADMIN to add any Product");
        }

        CategoryEO fetched = categoryRepo.findByName(productRequest.getCategory());
        if (fetched == null) {
            throw new ResourceNotFoundException("CATEGORY", "categoryName", productRequest.getCategory());
        }

        ProductEO goingToSaved = modelMapper.map(productRequest, ProductEO.class);
        goingToSaved.setProductId("ESTORE" + UUID.randomUUID().toString());

        if (productRequest.getImage() != null) {
            Map<?, ?> image = imageUploaderService.uploadFile(productRequest.getImage(), productFolder);
            if (image != null) {
                goingToSaved.setImageUrl(image.get("url").toString());
            }
        }

        ProductEO saved = productRepo.save(goingToSaved);

        ProductResponse response = modelMapper.map(saved, ProductResponse.class);

        return response;

    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public PaginatedResponse getAllProducts(
            Integer pageNo,
            Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<ProductEO> productPage = productRepo.findAll(pageable);

        List<ProductResponse> productResponses = productPage.getContent().stream()
                .map(e -> modelMapper.map(e, ProductResponse.class))
                .toList();

        productResponses.forEach(priceModifierUtil::modifyPrice);

        return PaginatedResponse.builder()
                .content(productResponses)
                .currentPage(productPage.getNumber())
                .isLastPage(productPage.isLast())
                .totalPages(productPage.getTotalPages())
                .totalItems((int) productPage.getTotalElements())
                .currentPageItemsNumber(productPage.getNumberOfElements())
                .pageSize(productPage.getSize()).build();
    }

    @Override
    public PaginatedResponse applyFilter(
            Integer pageNo,
            Integer pageSize,
            String sortBy,
            String sortDir,
            String category,
            Double lowerBound,
            Double upperBound) {

        boolean correctSortOrder = (sortDir != null && !sortDir.isBlank())
                &&
                sortDir.equals("ASC") || sortDir.equals("DESC");

        String SORT_DIR = correctSortOrder ? sortDir : PageConstants.DEFAULT_SORT_BY;

        Sort sort = Sort.by(Sort.Direction.fromString(SORT_DIR),sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        var specifiation = SpecificationService.getWithFilter(category, lowerBound, upperBound);

        Page<ProductEO> filtered = productRepo.findAll(specifiation, pageable);

        List<ProductResponse> productResponses = filtered.getContent().stream()
                .map(e -> modelMapper.map(e, ProductResponse.class))
                .toList();

        productResponses.forEach(priceModifierUtil::modifyPrice);


        return PaginatedResponse.builder()
                .content(productResponses)
                .currentPage(filtered.getNumber())
                .isLastPage(filtered.isLast())
                .totalPages(filtered.getTotalPages())
                .totalItems((int) filtered.getTotalElements())
                .currentPageItemsNumber(filtered.getNumberOfElements())
                .pageSize(filtered.getSize()).build();

    }

    @Override
    public ProductResponse getProductByProductId(String productId) {

        ProductEO fetched = productRepo.findByProductId(productId);

        if(fetched == null){
            throw new ResourceNotFoundException("PRODUCT", "productId", productId);
        }
        ProductResponse response = modelMapper.map(fetched, ProductResponse.class);
        priceModifierUtil.modifyPrice(response);
        return response;

    }

}

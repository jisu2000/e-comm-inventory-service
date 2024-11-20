package org.jisu.e_comm_inventory_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.jisu.e_comm_inventory_service.dto.request.DiscountRequest;
import org.jisu.e_comm_inventory_service.dto.response.ApiResponse;
import org.jisu.e_comm_inventory_service.dto.response.DiscountResponse;
import org.jisu.e_comm_inventory_service.exception.InvalidRequestException;
import org.jisu.e_comm_inventory_service.exception.ResourceNotFoundException;
import org.jisu.e_comm_inventory_service.model.DiscountEO;
import org.jisu.e_comm_inventory_service.model.ProductEO;
import org.jisu.e_comm_inventory_service.repo.DiscountRepo;
import org.jisu.e_comm_inventory_service.repo.ProductRepo;
import org.jisu.e_comm_inventory_service.service.DiscountService;
import org.jisu.e_comm_inventory_service.utils.DiscountRequestValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRequestValidator discountRequestValidator;
    private final DiscountRepo discountRepo;
    private final ModelMapper modelMapper;
    private final ProductRepo productRepo;

    @Override
    public DiscountResponse addDiscount(String authHeader, DiscountRequest discountRequest) {

        //TO-DO add authentication

        ProductEO fetched = productRepo.findByProductId(discountRequest.getProductId());

        if(fetched == null){
            throw new ResourceNotFoundException("Product","productId", discountRequest.getProductId());
        }

        boolean validDiscount = discountRequestValidator.validateDiscountRequest(discountRequest);

        if(!validDiscount){
            throw new InvalidRequestException("There is already a Discount which is overlapping the Current Request");
        }

        DiscountEO goingToBeSaved = new DiscountEO();
        goingToBeSaved.setDiscount(discountRequest.getDiscount());
        goingToBeSaved.setProductId(discountRequest.getProductId());
        goingToBeSaved.setStartDate(discountRequest.getStartDate());
        goingToBeSaved.setEndDate(discountRequest.getEndDate());
        DiscountEO saved = discountRepo.save(goingToBeSaved);

        DiscountResponse response = modelMapper.map(saved,DiscountResponse.class);

        return response;

    }

    @Override
    public List<DiscountResponse> getAllDiscountOfAProduct(String productId) {

        List<DiscountEO> fetched = discountRepo.findByProductId(productId);

        List<DiscountResponse> response = fetched
                .stream()
                .map(e -> modelMapper.map(e, DiscountResponse.class))
                .toList();

        return response;
    }

    @Override
    public ApiResponse<?> deleteDiscount(String authHeader, Integer id) {

        //TO-DO add authentication

        try {
            discountRepo.deleteById(id);
            return new ApiResponse<String>("Discount deleted Successfully");
        } catch (Exception e) {

        }

        throw new InvalidRequestException("Discount can not be deleted");

    }
}

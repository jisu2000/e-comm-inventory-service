package org.jisu.e_comm_inventory_service.utils;

import lombok.RequiredArgsConstructor;
import org.jisu.e_comm_inventory_service.dto.request.DiscountRequest;
import org.jisu.e_comm_inventory_service.exception.InvalidRequestException;
import org.jisu.e_comm_inventory_service.model.DiscountEO;
import org.jisu.e_comm_inventory_service.repo.DiscountRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiscountRequestValidator {

    private final ModelMapper modelMapper;
    private final DiscountRepo discountRepo;

    public boolean validateDiscountRequest(DiscountRequest discountRequest){

        if(discountRequest.getStartDate().isAfter(discountRequest.getEndDate())){
            throw new InvalidRequestException("StartDate can not be before EndDate");
        }
        List<DiscountEO> previousDiscount =
                discountRepo.findByProductId(discountRequest.getProductId());

        if(previousDiscount.isEmpty()){
            return true;
        }

        List<DiscountRequest> alreadyExist
                = previousDiscount.stream().map(e -> modelMapper.map(e, DiscountRequest.class))
                .collect(Collectors.toList());

        alreadyExist.add(discountRequest);

        alreadyExist.sort(Comparator.comparing(DiscountRequest::getStartDate));

        for(int i = 1; i<alreadyExist.size();i++){
            DiscountRequest prev = alreadyExist.get(i);
            DiscountRequest next = alreadyExist.get(i-1);

            if(!isSafe(prev,next)){
                return false;
            }
        }

        return true;
    }


    private boolean isSafe(DiscountRequest d1, DiscountRequest d2){

        return
                (d1.getStartDate().isBefore(d2.getStartDate()) && d1.getEndDate().isBefore(d2.getStartDate()))

                ||

                (d1.getStartDate().isAfter(d2.getEndDate()) && d1.getEndDate().isAfter(d2.getEndDate()));

    }


}
